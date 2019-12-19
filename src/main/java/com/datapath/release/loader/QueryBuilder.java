package com.datapath.release.loader;

import com.datapath.release.loader.containers.*;
import com.datapath.release.loader.query.InsertQuery;
import com.datapath.release.loader.query.Query;
import com.datapath.release.loader.query.SelectQuery;
import org.springframework.stereotype.Component;

import static com.datapath.release.loader.query.Query.*;
import static java.util.Objects.isNull;

@Component
public class QueryBuilder {

    private String getPeriodStartDate(Period period) {
        return period == null ? null : period.getStartDate();
    }

    private String getPeriodEndDate(Period period) {
        return period == null ? null : period.getEndDate();
    }

    String existRelease(Release release) {
        SelectQuery selectQuery = select().columns("id")
                .from("release")
                .where()
                .condition("ocid", " = ", release.getOcid());
        return select().exists(selectQuery).build();
    }

    String updateRelease(Release release) {
        return update().table("release")
                .column("datetime", release.getDate())
                .column("outer_id", release.getId())
                .column("initiation_type", release.getInitiationType())
                .column("lang", release.getLanguage())
                .column("ocid", release.getOcid())
                .where().condition("ocid", " = ", release.getOcid())
                .returning("id").build();
    }

    String insertRelease(Release release) {
        return insert().into("release")
                .column("datetime", release.getDate())
                .column("outer_id", release.getId())
                .column("initiation_type", release.getInitiationType())
                .column("lang", release.getLanguage())
                .column("ocid", release.getOcid())
                .returning("id").build();
    }

    String existBuyer(Buyer buyer) {
        SelectQuery selectQuery = select().columns("id")
                .from("buyer")
                .where()
                .condition("outer_id", " = ", buyer.getIdentifier().getId());
        return select().exists(selectQuery).build();
    }

    String insertBuyer(Buyer buyer) {
        return new InsertQuery().into("buyer")
                .column("name", buyer.getName())
                .column("outer_id", buyer.getIdentifier().getId())
                .column("legal_name", buyer.getIdentifier().getLegalName())
                .column("scheme", buyer.getIdentifier().getScheme())
                .column("uri", buyer.getIdentifier().getUri())
                .column("country_name", buyer.getAddress().getCountryName())
                .column("locality", buyer.getAddress().getLocality())
                .column("postal_code", buyer.getAddress().getPostalCode())
                .column("region", buyer.getAddress().getRegion())
                .column("street_address", buyer.getAddress().getStreetAddress())
                .returning("id")
                .build();
    }

    String updateBuyer(Buyer buyer) {
        return update().table("buyer")
                .column("name", buyer.getName())
                .column("outer_id", buyer.getIdentifier().getId())
                .column("legal_name", buyer.getIdentifier().getLegalName())
                .column("scheme", buyer.getIdentifier().getScheme())
                .column("uri", buyer.getIdentifier().getUri())
                .column("country_name", buyer.getAddress().getCountryName())
                .column("locality", buyer.getAddress().getLocality())
                .column("postal_code", buyer.getAddress().getPostalCode())
                .column("region", buyer.getAddress().getRegion())
                .column("street_address", buyer.getAddress().getStreetAddress())
                .where().condition("outer_id", " = ", buyer.getIdentifier().getId())
                .returning("id")
                .build();
    }

    String existTender(String tenderOuterId, Integer releaseId) {
        SelectQuery selectQuery = select().columns("id")
                .from("tender")
                .where()
                .condition("outer_id", " = ", tenderOuterId)
                .and()
                .condition("release_id", " = ", releaseId);
        return select().exists(selectQuery).build();
    }

    String insertTender(Tender tender, Integer buyerId, Integer releaseId) {
        return insert().into("tender")
                .column("award_criteria", tender.getAwardCriteria())
                .column("award_start_date", tender.getAwardStartDate())
                .column("award_end_date", tender.getAwardEndDate())
                .column("cause", tender.getCause())
                .column("cause_description", tender.getCauseDescription())
                .column("complaint_start_date", tender.getComplaintStartDate())
                .column("complaint_end_date", tender.getComplaintEndDate())
                .column("current_stage", tender.getCurrentStage())
                .column("description", tender.getDescription())
                .column("enquiry_start_date", tender.getEnquiryStartDate())
                .column("enquiry_end_date", tender.getEnquiryEndDate())
                .column("guarantee_amount", tender.getGuaranteeAmount())
                .column("guarantee_currency", tender.getGuaranteeCurrency())
                .column("outer_id", tender.getId())
                .column("number_of_tenderers", tender.getNumberOfTenderers())
                .column("pending_cancellation", tender.getPendingCancellation())
                .column("procurement_method", tender.getProcurementMethod())
                .column("procurement_method_type", tender.getProcurementMethodType())
                .column("buyer_id", buyerId)
                .column("qualification_start_date", tender.getQualificationStartDate())
                .column("qualification_end_date", tender.getQualificationEndDate())
                .column("stage2tenderid", tender.getStage2TenderID())
                .column("status", tender.getStatus())
                .column("submission_method_details", tender.getSubmissionMethodDetails())
                .column("submission_method_details", tender.getSubmissionMethodDetails())
                .column("tenderid", tender.getTenderID())
                .column("tender_start_date", tender.getTenderStartDate())
                .column("tender_end_date", tender.getTenderEndDate())
                .column("title", tender.getTitle())
                .column("amount", tender.getValueAmount())
                .column("currency", tender.getValueCurrency())
                .column("value_added_tax_included", tender.getValueAddedTaxIncluded())
                .column("release_id", releaseId)
                .column("buyer_kind", isNull(tender.getProcuringEntity()) ? null : tender.getProcuringEntity().getKind())
                .returning("id")
                .build();
    }

    String updateTender(Tender tender, Integer buyerId, Integer releaseId) {
        return update().table("tender")
                .column("award_criteria", tender.getAwardCriteria())
                .column("award_start_date", tender.getAwardStartDate())
                .column("award_end_date", tender.getAwardEndDate())
                .column("cause", tender.getCause())
                .column("cause_description", tender.getCauseDescription())
                .column("complaint_start_date", tender.getComplaintStartDate())
                .column("complaint_end_date", tender.getComplaintEndDate())
                .column("current_stage", tender.getCurrentStage())
                .column("description", tender.getDescription())
                .column("enquiry_start_date", tender.getEnquiryStartDate())
                .column("enquiry_end_date", tender.getEnquiryEndDate())
                .column("guarantee_amount", tender.getGuaranteeAmount())
                .column("guarantee_currency", tender.getGuaranteeCurrency())
                .column("outer_id", tender.getId())
                .column("number_of_tenderers", tender.getNumberOfTenderers())
                .column("pending_cancellation", tender.getPendingCancellation())
                .column("procurement_method", tender.getProcurementMethod())
                .column("procurement_method_type", tender.getProcurementMethodType())
                .column("buyer_id", buyerId)
                .column("qualification_start_date", tender.getQualificationStartDate())
                .column("qualification_end_date", tender.getQualificationEndDate())
                .column("stage2tenderid", tender.getStage2TenderID())
                .column("status", tender.getStatus())
                .column("submission_method_details", tender.getSubmissionMethodDetails())
                .column("tenderid", tender.getTenderID())
                .column("tender_start_date", tender.getTenderStartDate())
                .column("tender_end_date", tender.getTenderEndDate())
                .column("title", tender.getTitle())
                .column("amount", tender.getValueAmount())
                .column("currency", tender.getValueCurrency())
                .column("value_added_tax_included", tender.getValueAddedTaxIncluded())
                .column("release_id", releaseId)
                .column("buyer_kind", isNull(tender.getProcuringEntity()) ? null : tender.getProcuringEntity().getKind())
                .where().condition("outer_id", " = ", tender.getId())
                .and().condition("tenderid", " = ", tender.getTenderID())
                .returning("id")
                .build();
    }

    String insertAutoCreatedLot(Integer tenderId) {
        return "INSERT INTO lot(outer_id,tender_id,status,amount,currency,value_added_tax_included) " +
                "SELECT 'auto created',id,status, amount, currency, value_added_tax_included " +
                "FROM tender WHERE id = " + tenderId;
    }

    String updateAutoCreatedLot(Integer tenderId) {
        return "UPDATE lot l SET\n" +
                "status = t.status,\n" +
                "amount = t.amount,\n" +
                "currency = t.currency,\n" +
                "value_added_tax_included = t.value_added_tax_included,\n" +
                "guarantee_amount = t.guarantee_amount,\n" +
                "guarantee_currency = t.guarantee_currency\n" +
                "FROM tender t\n" +
                "WHERE l.tender_id = t.id AND t.id = " + tenderId;
    }

    String existLot(String lotId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("lot")
                .where()
                .condition("outer_id", " = ", lotId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertLot(Lot lot, Integer tenderId) {
        return insert().into("lot")
                .column("description", lot.getDescription())
                .column("guarantee_amount", lot.getGuaranteeAmount())
                .column("guarantee_currency", lot.getGuaranteeCurrency())
                .column("outer_id", lot.getId())
                .column("pending_cancellation", lot.getPendingCancellation())
                .column("status", lot.getStatus())
                .column("title", lot.getTitle())
                .column("amount", lot.getValueAmount())
                .column("currency", lot.getValueCurrency())
                .column("value_added_tax_included", lot.getValueAddedTaxIncluded())
                .column("tender_id", tenderId)
                .build();
    }

    String updateLot(Lot lot, Integer tenderId) {
        return Query.update()
                .table("lot")
                .column("description", lot.getDescription())
                .column("guarantee_amount", lot.getGuaranteeAmount())
                .column("guarantee_currency", lot.getGuaranteeCurrency())
                .column("outer_id", lot.getId())
                .column("pending_cancellation", lot.getPendingCancellation())
                .column("status", lot.getStatus())
                .column("title", lot.getTitle())
                .column("amount", lot.getValueAmount())
                .column("currency", lot.getValueCurrency())
                .column("value_added_tax_included", lot.getValueAddedTaxIncluded())
                .column("tender_id", tenderId)
                .where()
                .condition("outer_id", " = ", lot.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .build();
    }

    String existTenderItem(Item item, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("tender_item")
                .where()
                .condition("outer_id", " = ", item.getId())
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertTenderItem(Item item, Long lotId, Integer tenderId) {
        return insert().into("tender_item")
                .column("classification_id", item.getClassification().getId())
                .column("description", item.getDescription())
                .column("outer_id", item.getId())
                .column("lot_id", lotId)
                .column("tender_id", tenderId)
                .column("delivery_country_name", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName())
                .column("delivery_country_name_en", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName_en())
                .column("delivery_country_name_ru", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName_ru())
                .column("delivery_locality", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getLocality())
                .column("delivery_postal_code", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getPostalCode())
                .column("delivery_region", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getRegion())
                .column("delivery_street_address", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getStreetAddress())
                .column("delivery_start_date", getPeriodStartDate(item.getDeliveryDate()))
                .column("delivery_end_date", getPeriodEndDate(item.getDeliveryDate()))
                .column("quantity", item.getQuantity())
                .column("unit_name", item.getUnit() == null ? null : item.getUnit().getName())
                .build();
    }

    String updateTenderItem(Item item, Long lotId, Integer tenderId) {
        return update().table("tender_item")
                .column("classification_id", item.getClassification().getId())
                .column("description", item.getDescription())
                .column("outer_id", item.getId())
                .column("lot_id", lotId)
                .column("tender_id", tenderId)
                .column("delivery_country_name", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName())
                .column("delivery_country_name_en", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName_en())
                .column("delivery_country_name_ru", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getCountryName_ru())
                .column("delivery_locality", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getLocality())
                .column("delivery_postal_code", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getPostalCode())
                .column("delivery_region", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getRegion())
                .column("delivery_street_address", item.getDeliveryAddress() == null ? null : item.getDeliveryAddress().getStreetAddress())
                .column("delivery_start_date", getPeriodStartDate(item.getDeliveryDate()))
                .column("delivery_end_date", getPeriodEndDate(item.getDeliveryDate()))
                .column("quantity", item.getQuantity())
                .column("unit_name", item.getUnit() == null ? null : item.getUnit().getName())
                .where()
                .condition("outer_id", " = ", item.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .build();
    }

    String existBidder(Tenderer bidder) {
        SelectQuery selectQuery = select().columns("id")
                .from("bidder")
                .where()
                .condition("outer_id", " = ", bidder.getIdentifier().getId());
        return select().exists(selectQuery).build();
    }

    String updateBidder(Tenderer tenderer) {
        return update().table("bidder").column("name", tenderer.getName())
                .column("outer_id", tenderer.getIdentifier().getId())
                .column("legal_name", tenderer.getIdentifier().getLegalName())
                .column("scheme", tenderer.getIdentifier().getScheme())
                .column("uri", tenderer.getIdentifier().getUri())
                .column("country_name", tenderer.getCountryName())
                .column("locality", tenderer.getLocality())
                .column("postal_code", tenderer.getPostalCode())
                .column("region", tenderer.getRegion())
                .column("street_address", tenderer.getStreetAddress())
                .column("phone", tenderer.getContactPoint() == null ? null : tenderer.getContactPoint().getTelephone())
                .column("email", tenderer.getContactPoint() == null ? null : tenderer.getContactPoint().getEmail())
                .where()
                .condition("outer_id", " = ", tenderer.getIdentifier().getId())
                .returning("id")
                .build();
    }

    String insertBidder(Tenderer bidder) {
        return insert().into("bidder")
                .column("name", bidder.getName())
                .column("outer_id", bidder.getIdentifier().getId())
                .column("legal_name", bidder.getIdentifier().getLegalName())
                .column("scheme", bidder.getIdentifier().getScheme())
                .column("uri", bidder.getIdentifier().getUri())
                .column("country_name", bidder.getCountryName())
                .column("locality", bidder.getLocality())
                .column("postal_code", bidder.getPostalCode())
                .column("region", bidder.getRegion())
                .column("street_address", bidder.getStreetAddress())
                .column("phone", bidder.getContactPoint() == null ? null : bidder.getContactPoint().getTelephone())
                .column("email", bidder.getContactPoint() == null ? null : bidder.getContactPoint().getEmail())
                .returning("id")
                .build();
    }

    String existDocument(String documentId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("document")
                .where()
                .condition("outer_id", " = ", documentId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertDocument(Integer tenderId, Document document, DocumentKind kind, Long lotId) {
        return insert().into("document")
                .column("kind_id", kind.getId())
                .column("date_modified", document.getDateModified())
                .column("date_published", document.getDatePublished())
                .column("description", document.getDescription())
                .column("document_scope", document.getDocumentScope())
                .column("document_type", document.getDocumentType())
                .column("format", document.getFormat())
                .column("outer_id", document.getId())
                .column("lang", document.getLanguage())
                .column("lot_id", lotId)
                .column("title", document.getTitle())
                .column("tender_id", tenderId)
                .returning("id")
                .build();
    }

    String updateDocument(Integer tenderId, Document document, DocumentKind kind, Long lotId) {
        return update().table("document")
                .column("kind_id", kind.getId())
                .column("date_modified", document.getDateModified())
                .column("date_published", document.getDatePublished())
                .column("description", document.getDescription())
                .column("document_scope", document.getDocumentScope())
                .column("document_type", document.getDocumentType())
                .column("format", document.getFormat())
                .column("outer_id", document.getId())
                .column("lang", document.getLanguage())
                .column("lot_id", lotId)
                .column("title", document.getTitle())
                .column("tender_id", tenderId)
                .where()
                .condition("outer_id", " = ", document.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .returning("id")
                .build();
    }

    String existBid(String bidId, Long lotId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("bid")
                .where()
                .condition("outer_id", " = ", bidId)
                .and()
                .condition("lot_id", " = ", lotId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertBid(BidDetail bid, Long lotId, Integer bidderId, Integer tenderId) {
        return insert().into("bid")
                .column("datetime", bid.getDate())
                .column("outer_id", bid.getId())
                .column("participation_url", bid.getParticipationUrl())
                .column("lot_id", lotId)
                .column("self_eligible", bid.getSelfEligible())
                .column("self_qualified", bid.getSelfQualified())
                .column("status", bid.getStatus())
                .column("subcontracting_details", bid.getSubcontractingDetails())
                .column("bidder_id", bidderId)
                .column("amount", bid.getValueAmount())
                .column("currency", bid.getValueCurrency())
                .column("value_added_tax_included", bid.getValueAddedTaxIncluded())
                .column("tender_id", tenderId)
                .returning("id")
                .build();
    }

    String updateBid(BidDetail bid, Long lotId, Integer bidderId, Integer tenderId) {
        return update().table("bid")
                .column("datetime", bid.getDate())
                .column("participation_url", bid.getParticipationUrl())
                .column("self_eligible", bid.getSelfEligible())
                .column("self_qualified", bid.getSelfQualified())
                .column("status", bid.getStatus())
                .column("subcontracting_details", bid.getSubcontractingDetails())
                .column("bidder_id", bidderId)
                .column("amount", bid.getValueAmount())
                .column("currency", bid.getValueCurrency())
                .column("value_added_tax_included", bid.getValueAddedTaxIncluded())
                .where()
                .condition("outer_id", " = ", bid.getId())
                .and()
                .condition("lot_id", " = ", lotId)
                .and()
                .condition("tender_id", " = ", tenderId)
                .returning("id")
                .build();
    }

    String existComplaint(String complaintId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("complaint")
                .where()
                .condition("outer_id", " = ", complaintId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertComplaint(ComplaintKind kind, Complaint complaint, Integer bidderId, Long lotId, Integer tenderId) {
        return insert().into("complaint")
                .column("kind_id", kind.getId())
                .column("acceptance", complaint.getAcceptance())
                .column("bidder_id", bidderId)
                .column("cancellation_reason", complaint.getCancellationReason())
                .column("datetime", complaint.getDate())
                .column("date_answered", complaint.getDateAnswered())
                .column("date_decision", complaint.getDateDecision())
                .column("date_escalated", complaint.getDateEscalated())
                .column("date_submitted", complaint.getDateSubmitted())
                .column("description", complaint.getDescription())
                .column("outer_id", complaint.getId())
                .column("lot_id", lotId)
                .column("resolution", complaint.getResolution())
                .column("resolution_type", complaint.getResolutionType())
                .column("satisfied", complaint.getSatisfied())
                .column("status", complaint.getStatus())
                .column("tenderer_action", complaint.getTendererAction())
                .column("tenderer_action_date", complaint.getTendererActionDate())
                .column("title", complaint.getTitle())
                .column("type", complaint.getType())
                .column("tender_id", tenderId)
                .returning("id")
                .build();
    }

    String updateComplaint(ComplaintKind kind, Complaint complaint, Integer bidderId, Long lotId, Integer tenderId) {
        return update().table("complaint")
                .column("kind_id", kind.getId())
                .column("acceptance", complaint.getAcceptance())
                .column("bidder_id", bidderId)
                .column("cancellation_reason", complaint.getCancellationReason())
                .column("datetime", complaint.getDate())
                .column("date_answered", complaint.getDateAnswered())
                .column("date_decision", complaint.getDateDecision())
                .column("date_escalated", complaint.getDateEscalated())
                .column("date_submitted", complaint.getDateSubmitted())
                .column("description", complaint.getDescription())
                .column("outer_id", complaint.getId())
                .column("lot_id", lotId)
                .column("resolution", complaint.getResolution())
                .column("resolution_type", complaint.getResolutionType())
                .column("satisfied", complaint.getSatisfied())
                .column("status", complaint.getStatus())
                .column("tenderer_action", complaint.getTendererAction())
                .column("tenderer_action_date", complaint.getTendererActionDate())
                .column("title", complaint.getTitle())
                .column("type", complaint.getType())
                .column("tender_id", tenderId)
                .where()
                .condition("outer_id", " = ", complaint.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .returning("id")
                .build();
    }

    String existAuction(Long lotId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("auction")
                .where()
                .condition("lot_id", " = ", lotId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertAuction(Auction auction, Long lotId, Integer tenderId) {
        return insert().into("auction")
                .column("minimal_step_amount", auction.getMinimalStep() != null ? auction.getMinimalStep().getAmount() : null)
                .column("minimal_step_currency", auction.getMinimalStep() != null ? auction.getMinimalStep().getCurrency() : null)
                .column("minimal_step_value_added_tax_included", auction.getMinimalStep() != null ? auction.getMinimalStep().getValueAddedTaxIncluded() : null)
                .column("start_date", auction.getPeriod() != null ? auction.getPeriod().getStartDate() : null)
                .column("end_date", auction.getPeriod() != null ? auction.getPeriod().getEndDate() : null)
                .column("url", auction.getUrl())
                .column("lot_id", lotId)
                .column("tender_id", tenderId)
                .build();
    }

    String updateAuction(Auction auction, Long lotId, Integer tenderId) {
        return update().table("auction")
                .column("minimal_step_amount", auction.getMinimalStep() != null ? auction.getMinimalStep().getAmount() : null)
                .column("minimal_step_currency", auction.getMinimalStep() != null ? auction.getMinimalStep().getCurrency() : null)
                .column("minimal_step_value_added_tax_included", auction.getMinimalStep() != null ? auction.getMinimalStep().getValueAddedTaxIncluded() : null)
                .column("start_date", auction.getPeriod() != null ? auction.getPeriod().getStartDate() : null)
                .column("end_date", auction.getPeriod() != null ? auction.getPeriod().getEndDate() : null)
                .column("url", auction.getUrl())
                .where()
                .condition("lot_id", " = ", lotId)
                .and()
                .condition("tender_id", " = ", tenderId)
                .build();
    }

    String existAward(String awardId, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("award")
                .where()
                .condition("outer_id", " = ", awardId)
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String insertAward(Award award, Integer bidderId, Long lotId, Integer tenderId) {
        return insert().into("award")
                .column("complaint_start_date", award.getComplaintPeriod() != null ? award.getComplaintPeriod().getStartDate() : null)
                .column("complaint_end_date", award.getComplaintPeriod() != null ? award.getComplaintPeriod().getEndDate() : null)
                .column("datetime", award.getDate())
                .column("description", award.getDescription())
                .column("eligible", award.getEligible())
                .column("outer_id", award.getId())
                .column("lot_id", lotId)
                .column("qualified", award.getQualified())
                .column("status", award.getStatus())
                .column("subcontracting_details", award.getSubcontractingDetails())
                .column("bidder_id", bidderId)
                .column("title", award.getTitle())
                .column("amount", award.getValue().getAmount())
                .column("currency", award.getValue().getCurrency())
                .column("value_added_tax_included", award.getValue().getValueAddedTaxIncluded())
                .column("tender_id", tenderId)
                .returning("id")
                .build();
    }

    String updateAward(Award award, Integer bidderId, Long lotId, Integer tenderId) {
        return update().table("award")
                .column("complaint_start_date", award.getComplaintPeriod() != null ? award.getComplaintPeriod().getStartDate() : null)
                .column("complaint_end_date", award.getComplaintPeriod() != null ? award.getComplaintPeriod().getEndDate() : null)
                .column("datetime", award.getDate())
                .column("description", award.getDescription())
                .column("eligible", award.getEligible())
                .column("lot_id", lotId)
                .column("qualified", award.getQualified())
                .column("status", award.getStatus())
                .column("subcontracting_details", award.getSubcontractingDetails())
                .column("bidder_id", bidderId)
                .column("title", award.getTitle())
                .column("amount", award.getValue().getAmount())
                .column("currency", award.getValue().getCurrency())
                .column("value_added_tax_included", award.getValue().getValueAddedTaxIncluded())
                .where()
                .condition("outer_id", " = ", award.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .returning("id")
                .build();
    }

    String existContract(String contractId, Long awardId) {
        SelectQuery selectQuery = select().columns("id")
                .from("contract")
                .where()
                .condition("outer_id", " = ", contractId)
                .and()
                .condition("award_id", " = ", awardId);
        return select().exists(selectQuery).build();
    }

    String insertContract(Contract contract, Long awardId) {
        return insert().into("contract")
                .column("award_id", awardId)
                .column("contract_id", contract.getContractID())
                .column("contract_number", contract.getContractNumber())
                .column("date_signed", contract.getDateSigned())
                .column("description", contract.getDescription())
                .column("outer_id", contract.getId())
                .column("start_date", contract.getPeriod() == null ? null : contract.getPeriod().getStartDate())
                .column("end_date", contract.getPeriod() == null ? null : contract.getPeriod().getEndDate())
                .column("status", contract.getStatus())
                .column("title", contract.getTitle())
                .column("amount", contract.getValueAmount())
                .column("currency", contract.getValueCurrency())
                .column("value_added_tax_included", contract.getValueAddedTaxIncluded())
                .returning("id")
                .build();
    }

    String updateContract(Contract contract, Long awardId) {
        return update().table("contract")
                .column("contract_id", contract.getContractID())
                .column("contract_number", contract.getContractNumber())
                .column("date_signed", contract.getDateSigned())
                .column("description", contract.getDescription())
                .column("start_date", contract.getPeriod() == null ? null : contract.getPeriod().getStartDate())
                .column("end_date", contract.getPeriod() == null ? null : contract.getPeriod().getEndDate())
                .column("status", contract.getStatus())
                .column("title", contract.getTitle())
                .column("amount", contract.getValueAmount())
                .column("currency", contract.getValueCurrency())
                .column("value_added_tax_included", contract.getValueAddedTaxIncluded())
                .where()
                .condition("outer_id", " = ", contract.getId())
                .and()
                .condition("award_id", " = ", awardId)
                .returning("id")
                .build();

    }

    String existEnquiry(Enquiry enquiry, Integer tenderId) {
        SelectQuery selectQuery = select().columns("id")
                .from("enquiry")
                .where()
                .condition("outer_id", " = ", enquiry.getId())
                .and()
                .condition("tender_id", " = ", tenderId);
        return select().exists(selectQuery).build();
    }

    String updateEnquiry(Enquiry enquiry, Integer bidderId, Integer tenderId) {
        return update().table("enquiry")
                .column("description", enquiry.getDescription())
                .column("title", enquiry.getTitle())
                .column("datetime", enquiry.getDate())
                .column("datetime_answered", enquiry.getDateAnswered())
                .column("answer", enquiry.getAnswer())
                .column("outer_id", enquiry.getId())
                .column("bidder_id", bidderId)
                .where().condition("outer_id", " = ", enquiry.getId())
                .and()
                .condition("tender_id", " = ", tenderId)
                .returning("id").build();
    }

    String insertEnquiry(Enquiry enquiry, Integer bidderId, Integer tenderId) {
        return insert().into("enquiry")
                .column("description", enquiry.getDescription())
                .column("title", enquiry.getTitle())
                .column("datetime", enquiry.getDate())
                .column("datetime_answered", enquiry.getDateAnswered())
                .column("answer", enquiry.getAnswer())
                .column("outer_id", enquiry.getId())
                .column("bidder_id", bidderId)
                .column("tender_id", tenderId)
                .returning("id").build();
    }
}