package com.datapath.release.loader.containers;

import lombok.Data;

import java.util.List;

@Data
public class BidDetail {

    private String date;
    private List<Document> documents;
    private List<Document> eligibilityDocuments;
    private String id;
    private String participationUrl;
    private String relatedLot;
    private Boolean selfEligible;
    private Boolean selfQualified;
    private String status;
    private String subcontractingDetails;
    private List<Tenderer> tenderers;
    private Value value;

    public Double getValueAmount() {
        return value == null ? null : value.getAmount();
    }

    public String getValueCurrency() {
        return value == null ? null : value.getCurrency();
    }

    public Boolean getValueAddedTaxIncluded() {
        return value == null ? null : value.getValueAddedTaxIncluded();
    }


}