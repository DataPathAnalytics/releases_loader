package com.datapath.release.loader;

import com.datapath.release.loader.containers.*;
import com.datapath.release.loader.query.Query;
import com.datapath.release.loader.query.SelectQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.datapath.release.loader.query.Query.select;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class DatabaseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDAO.class);
    private static final String AUTO_CREATED = "auto created";
    private JdbcTemplate jdbcTemplate;
    private QueryBuilder queryBuilder;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    @Transactional
    public void saveRelease(Release release) {
        if (release.getTender().getProcurementMethodType().equalsIgnoreCase("reporting") &&
                release.getTender().getProcurementMethod().equalsIgnoreCase("limited") &&
                !release.getTender().getStatus().equalsIgnoreCase("complete")
                ) {
            LOGGER.warn("Tender with id {} not valid for saving. " +
                            "It has reporting procurement method type, limited procurement method and status not equal complete",
                    release.getTender().getId());
            return;
        }
        Integer releaseId = insertRelease(release);
        Integer buyerId = insertBuyer(release.getBuyer());
        Integer tenderId = insertTender(release.getTender(), buyerId, releaseId);
        insertLots(release.getTender().getLots(), tenderId, release.getTender().getGuarantee());
        insertTenderItems(release.getTender().getItems(), tenderId);
        insertBidders(release.getTender().getTenderers(), tenderId);
        insertAwards(release.getAwards(), tenderId);
        insertContract(tenderId, release.getContracts());
        insertBids(release.getBids(), tenderId);
        insertAuctions(release.getTender().getAuctions(), tenderId);
        insertTenderDocuments(release.getTender().getDocuments(), tenderId);
        insertTenderComplaints(release.getTender().getComplaints(), tenderId);
        insertEnquiries(release.getTender().getEnquiries(), tenderId);
    }

    private void insertEnquiries(List<Enquiry> enquiries, Integer tenderId) {
        if (!isEmpty(enquiries)) {
            for (Enquiry enquiry : enquiries) {
                Integer bidderId = insertBidder(enquiry.getAuthor());
                String existSql = queryBuilder.existEnquiry(enquiry, tenderId);
                Boolean exist = jdbcTemplate.queryForObject(existSql, Boolean.class);
                if (exist) {
                    String query = queryBuilder.updateEnquiry(enquiry, bidderId, tenderId);
                    jdbcTemplate.queryForObject(query, Integer.class);
                } else {
                    String query = queryBuilder.insertEnquiry(enquiry, bidderId, tenderId);
                    jdbcTemplate.queryForObject(query, Integer.class);
                }
            }
        }
    }

    private Integer insertRelease(Release release) {
        String existSql = queryBuilder.existRelease(release);
        Boolean exist = jdbcTemplate.queryForObject(existSql, Boolean.class);
        if (exist) {
            String query = queryBuilder.updateRelease(release);
            return jdbcTemplate.queryForObject(query, Integer.class);
        } else {
            String query = queryBuilder.insertRelease(release);
            return jdbcTemplate.queryForObject(query, Integer.class);
        }
    }

    private Integer insertBuyer(Buyer buyer) {
        if (isNull(buyer.getIdentifier().getId())) return null;

        String existQuery = queryBuilder.existBuyer(buyer);
        Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
        if (exist) {
            String query = queryBuilder.updateBuyer(buyer);
            return jdbcTemplate.queryForObject(query, Integer.class);
        } else {
            String query = queryBuilder.insertBuyer(buyer);
            return jdbcTemplate.queryForObject(query, Integer.class);
        }
    }

    private void insertAwardComplaints(Long awardId, List<Complaint> complaints, Integer tenderId) {
        if (!isEmpty(complaints)) {
            for (Complaint complaint : complaints) {
                Integer complaintId = insertComplaint(ComplaintKind.AWARD, complaint, tenderId);
                String query = "INSERT INTO award_complaint(award_id,complaint_id) VALUES (?,?) ON CONFLICT DO NOTHING";
                jdbcTemplate.update(query, awardId, complaintId);
            }
        }
    }

    private void insertTenderComplaints(List<Complaint> complaints, Integer tenderId) {
        if (!isEmpty(complaints)) {
            for (Complaint complaint : complaints) {
                insertComplaint(ComplaintKind.TENDER, complaint, tenderId);
            }
        }
    }

    private Integer insertComplaint(ComplaintKind kind, Complaint complaint, Integer tenderId) {
        Integer bidderId = insertBidder(complaint.getAuthor());
        Long lotId = getLotId(complaint.getRelatedLot(), tenderId);
        String existQuery = queryBuilder.existComplaint(complaint.getId(), tenderId);
        Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
        String query;
        if (exist) {
            query = queryBuilder.updateComplaint(kind, complaint, bidderId, lotId, tenderId);
        } else {
            query = queryBuilder.insertComplaint(kind, complaint, bidderId, lotId, tenderId);
        }
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    private void insertTenderDocuments(List<Document> documents, Integer tenderId) {
        if (!isEmpty(documents)) {
            for (Document document : documents) {
                insertDocument(tenderId, document, DocumentKind.TENDER);
            }
        }
    }

    private void insertAuctions(List<Auction> auctions, Integer tenderId) {
        if (!isEmpty(auctions)) {
            for (Auction auction : auctions) {
                Long lotId = getLotId(auction.getRelatedLot(), tenderId);
                String existQuery = queryBuilder.existAuction(lotId, tenderId);
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                if (exist) {
                    String query = queryBuilder.updateAuction(auction, lotId, tenderId);
                    jdbcTemplate.update(query);
                } else {
                    String query = queryBuilder.insertAuction(auction, lotId, tenderId);
                    jdbcTemplate.update(query);
                }
            }
        }
    }

    private void insertBids(Bids bids, Integer tenderId) {
        if (bids != null && bids.getDetails() != null) {
            for (BidDetail bid : bids.getDetails()) {

                Integer bidderId = isNull(bid.getTenderers()) ? null : insertBidder(bid.getTenderers().get(0));
                if (nonNull(bidderId)) {
                    insertTenderBidder(tenderId, bidderId);
                }

                Long lotId = getLotId(bid.getRelatedLot(), tenderId);
                String existQuery = queryBuilder.existBid(bid.getId(), lotId, tenderId);
                Long bidId;
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                if (exist) {
                    String query = queryBuilder.updateBid(bid, lotId, bidderId, tenderId);
                    bidId = jdbcTemplate.queryForObject(query, Long.class);
                } else {
                    String query = queryBuilder.insertBid(bid, lotId, bidderId, tenderId);
                    bidId = jdbcTemplate.queryForObject(query, Long.class);
                }
                if (!isEmpty(bid.getDocuments())) {
                    for (Document document : bid.getDocuments()) {
                        Long documentId = insertDocument(tenderId, document, DocumentKind.BID);
                        String query = "INSERT INTO bid_document(bid_id,document_id) VALUES (?,?) ON CONFLICT DO NOTHING";
                        jdbcTemplate.update(query, bidId, documentId);
                    }
                }
                if (!isEmpty(bid.getEligibilityDocuments())) {
                    for (Document document : bid.getEligibilityDocuments()) {
                        Long documentId = insertDocument(tenderId, document, DocumentKind.BID_ELIGIBILITY);
                        String query = "INSERT INTO bid_eligibility_document(bid_id,document_id) VALUES (?,?) ON CONFLICT DO NOTHING";
                        jdbcTemplate.update(query, bidId, documentId);
                    }
                }
            }
        }
    }

    private Long insertDocument(Integer tenderId, Document document, DocumentKind kind) {
        Long lotId = null;

        if (nonNull(document.getDocumentScope()) && document.getDocumentScope().equals("lot")) {
            lotId = getLotId(document.getRelatedItem(), tenderId);
        }

        if (nonNull(document.getDocumentScope()) && document.getDocumentScope().equals("item")) {
            lotId = jdbcTemplate.queryForObject("SELECT lot_id FROM tender_item WHERE outer_id = ? AND tender_id = ?", Long.class, document.getRelatedItem(), tenderId);
        }

        String existQuery = queryBuilder.existDocument(document.getId(), tenderId);
        Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
        if (exist) {
            String query = queryBuilder.updateDocument(tenderId, document, kind, lotId);
            return jdbcTemplate.queryForObject(query, Long.class);
        } else {
            String query = queryBuilder.insertDocument(tenderId, document, kind, lotId);
            return jdbcTemplate.queryForObject(query, Long.class);
        }
    }

    private Integer insertTender(Tender tender, Integer buyerId, Integer releaseId) {
        String existQuery = queryBuilder.existTender(tender.getId(), releaseId);
        Boolean existTender = jdbcTemplate.queryForObject(existQuery, Boolean.class);
        if (existTender) {
            String query = queryBuilder.updateTender(tender, buyerId, releaseId);
            return jdbcTemplate.queryForObject(query, Integer.class);
        } else {
            String query = queryBuilder.insertTender(tender, buyerId, releaseId);
            return jdbcTemplate.queryForObject(query, Integer.class);
        }
    }

    private void insertLots(List<Lot> lots, Integer tenderId, Guarantee tenderGuarantee) {
        if (!isEmpty(lots)) {
            for (Lot lot : lots) {
                if (lots.size() == 1 && isNull(lot.getGuarantee())) {
                    lot.setGuarantee(tenderGuarantee);
                }
                String existQuery = queryBuilder.existLot(lot.getId(), tenderId);
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                String query = exist ? queryBuilder.updateLot(lot, tenderId) : queryBuilder.insertLot(lot, tenderId);
                jdbcTemplate.update(query);
            }
        } else {
            String existQuery = queryBuilder.existLot(AUTO_CREATED, tenderId);
            Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
            if (!exist) {
                jdbcTemplate.update(queryBuilder.insertAutoCreatedLot(tenderId));
            } else {
                jdbcTemplate.update(queryBuilder.updateAutoCreatedLot(tenderId));
            }
        }
    }

    private void insertBidders(List<Tenderer> tenderers, Integer tenderId) {
        if (!isEmpty(tenderers)) {
            for (Tenderer tenderer : tenderers) {
                Integer bidderId = insertBidder(tenderer);
                insertTenderBidder(tenderId, bidderId);
            }
        }
    }

    private void insertTenderBidder(Integer tenderId, Integer bidderId) {
        if (bidderId != null) {
            String sql = "INSERT INTO tender_bidder(tender_id, bidder_id) VALUES (?,?) ON CONFLICT DO NOTHING";
            jdbcTemplate.update(sql, tenderId, bidderId);
        }
    }

    private Integer insertBidder(Tenderer tenderer) {
        if (isNull(tenderer) || isNull(tenderer.getIdentifier().getId())) return null;

        String existQuery = queryBuilder.existBidder(tenderer);
        Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
        if (exist) {
            String query = queryBuilder.updateBidder(tenderer);
            return jdbcTemplate.queryForObject(query, Integer.class);
        } else {
            String query = queryBuilder.insertBidder(tenderer);
            return jdbcTemplate.queryForObject(query, Integer.class);
        }
    }

    private void insertTenderItems(List<Item> items, Integer tenderId) {
        if (!isEmpty(items)) {
            for (Item item : items) {
                Long lotId = getLotId(item.getRelatedLot(), tenderId);

                String existQuery = queryBuilder.existTenderItem(item, tenderId);
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                if (exist) {
                    String query = queryBuilder.updateTenderItem(item, lotId, tenderId);
                    jdbcTemplate.update(query);
                } else {
                    String query = queryBuilder.insertTenderItem(item, lotId, tenderId);
                    jdbcTemplate.update(query);
                }
            }
        }
    }

    private Long getLotId(String outerId, Integer tenderId) {
        if (isNull(outerId)) {

            String lotCountQuery = select().columns("COUNT(id)").from("lot")
                    .where().condition("outer_id", " != ", AUTO_CREATED)
                    .and().condition("tender_id", " = ", tenderId).build();
            Integer lotsCount = jdbcTemplate.queryForObject(lotCountQuery, Integer.class);
            if (lotsCount > 0) {
                // Процедура не однолотовая значит ссылаться на какой либо лот не можем
                return null;
            }

            SelectQuery lotQuery = select().columns("id").from("lot")
                    .where().condition("outer_id", " = ", AUTO_CREATED)
                    .and().condition("tender_id", " = ", tenderId);

            String existQuery = select().exists(lotQuery).build();
            Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
            if (exist) {
                return jdbcTemplate.queryForObject(lotQuery.build(), Long.class);
            } else {
                // If auto created lot is absent in that tender, we can relate with something
                return null;
            }
        } else {
            String query = Query.select().columns("id").from("lot")
                    .where()
                    .condition("outer_id", " = ", outerId)
                    .and()
                    .condition("tender_id", " = ", tenderId)
                    .build();
            return jdbcTemplate.queryForObject(query, Long.class);
        }
    }

    private void insertAwards(List<Award> awards, Integer tenderId) {
        if (!isEmpty(awards)) {
            for (Award award : awards) {
                Integer bidderId = insertBidder(award.getSuppliers().get(0));
                insertTenderBidder(tenderId, bidderId);
                Long lotId = getLotId(award.getLotID(), tenderId);

                String existQuery = queryBuilder.existAward(award.getId(), tenderId);
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                String sql;
                if (exist) {
                    sql = queryBuilder.updateAward(award, bidderId, lotId, tenderId);
                } else {
                    sql = queryBuilder.insertAward(award, bidderId, lotId, tenderId);
                }
                Long awardId = jdbcTemplate.queryForObject(sql, Long.class);

                insertAwardComplaints(awardId, award.getComplaints(), tenderId);

                if (!isEmpty(award.getDocuments())) {
                    for (Document document : award.getDocuments()) {
                        Long documentId = insertDocument(tenderId, document, DocumentKind.AWARD);
                        String query = "INSERT INTO award_document(award_id,document_id) VALUES (?,?) ON CONFLICT DO NOTHING";
                        jdbcTemplate.update(query, awardId, documentId);
                    }
                }
            }
        }
    }

    private void insertContract(int tenderId, List<Contract> contracts) {
        if (!isEmpty(contracts)) {
            for (Contract contract : contracts) {
                Long awardId = jdbcTemplate.queryForObject("SELECT id FROM award WHERE outer_id = ?", Long.class, contract.getAwardID());

                String existQuery = queryBuilder.existContract(contract.getId(), awardId);
                Boolean exist = jdbcTemplate.queryForObject(existQuery, Boolean.class);
                int contractId;
                if (exist) {
                    String query = queryBuilder.updateContract(contract, awardId);
                    contractId = jdbcTemplate.queryForObject(query, Integer.class);
                } else {
                    String query = queryBuilder.insertContract(contract, awardId);
                    contractId = jdbcTemplate.queryForObject(query, Integer.class);
                }

                if (!isEmpty(contract.getDocuments())) {
                    for (Document document : contract.getDocuments()) {
                        Long documentId = insertDocument(tenderId, document, DocumentKind.CONTRACT);
                        String query = "INSERT INTO contract_document(contract_id,document_id) VALUES (?,?) ON CONFLICT DO NOTHING";
                        jdbcTemplate.update(query, contractId, documentId);
                    }
                }
            }
        }
    }

    void clearAutoCreatedLotsInMultiLotsTenders() {
        String sql = "SELECT clear_excess_autocreated_lots()";
        jdbcTemplate.update(sql);
    }
}