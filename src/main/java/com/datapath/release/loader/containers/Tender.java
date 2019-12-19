package com.datapath.release.loader.containers;

import java.util.List;

public class Tender {

    private List<Auction> auctions;
    private String awardCriteria;
    private Period awardPeriod;
    private String cause;
    private String causeDescription;
    private Period complaintPeriod;
    private List<Complaint> complaints;
    private String currentStage;
    private String description;
    private List<Document> documents;
    private List<Enquiry> enquiries;
    private Period enquiryPeriod;
    private List<Feature> features;
    private Guarantee guarantee;
    private String id;
    private List<Item> items;
    private List<Lot> lots;
    private Integer numberOfTenderers;
    private Boolean pendingCancellation;
    private String procurementMethod;
    private String procurementMethodType;
    private Buyer procuringEntity;
    private Period qualificationPeriod;
    private List<Qualification> qualifications;
    private List<ShortListedFirm> shortlistedFirms;
    private String stage2TenderID;
    private String status;
    private List<String> submissionMethod;
    private String submissionMethodDetails;
    private String tenderID;
    private Period tenderPeriod;
    private List<Tenderer> tenderers;
    private String title;
    private Value value;

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public String getAwardCriteria() {
        return awardCriteria;
    }

    public void setAwardCriteria(String awardCriteria) {
        this.awardCriteria = awardCriteria;
    }

    public Period getAwardPeriod() {
        return awardPeriod;
    }

    public String getAwardStartDate() {
        return awardPeriod == null ? null : awardPeriod.getStartDate();
    }

    public String getAwardEndDate() {
        return awardPeriod == null ? null : awardPeriod.getEndDate();
    }

    public void setAwardPeriod(Period awardPeriod) {
        this.awardPeriod = awardPeriod;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCauseDescription() {
        return causeDescription;
    }

    public void setCauseDescription(String causeDescription) {
        this.causeDescription = causeDescription;
    }

    public Period getComplaintPeriod() {
        return complaintPeriod;
    }

    public String getComplaintStartDate() {
        return complaintPeriod == null ? null : complaintPeriod.getStartDate();
    }

    public String getComplaintEndDate() {
        return complaintPeriod == null ? null : complaintPeriod.getEndDate();
    }

    public void setComplaintPeriod(Period complaintPeriod) {
        this.complaintPeriod = complaintPeriod;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public Period getEnquiryPeriod() {
        return enquiryPeriod;
    }

    public String getEnquiryStartDate() {
        return enquiryPeriod == null ? null : enquiryPeriod.getStartDate();
    }

    public String getEnquiryEndDate() {
        return enquiryPeriod == null ? null : enquiryPeriod.getEndDate();
    }

    public void setEnquiryPeriod(Period enquiryPeriod) {
        this.enquiryPeriod = enquiryPeriod;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public Long getGuaranteeAmount() {
        return guarantee == null ? null : guarantee.getAmount();
    }

    public String getGuaranteeCurrency() {
        return guarantee == null ? null : guarantee.getCurrency();
    }

    public void setGuarantee(Guarantee guarantee) {
        this.guarantee = guarantee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public Integer getNumberOfTenderers() {
        return numberOfTenderers;
    }

    public void setNumberOfTenderers(Integer numberOfTenderers) {
        this.numberOfTenderers = numberOfTenderers;
    }

    public Boolean getPendingCancellation() {
        return pendingCancellation;
    }

    public void setPendingCancellation(Boolean pendingCancellation) {
        this.pendingCancellation = pendingCancellation;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public String getProcurementMethodType() {
        return procurementMethodType;
    }

    public void setProcurementMethodType(String procurementMethodType) {
        this.procurementMethodType = procurementMethodType;
    }

    public Buyer getProcuringEntity() {
        return procuringEntity;
    }

    public void setProcuringEntity(Buyer procuringEntity) {
        this.procuringEntity = procuringEntity;
    }

    public Period getQualificationPeriod() {
        return qualificationPeriod;
    }

    public String getQualificationStartDate() {
        return qualificationPeriod == null ? null : qualificationPeriod.getStartDate();
    }

    public String getQualificationEndDate() {
        return qualificationPeriod == null ? null : qualificationPeriod.getEndDate();
    }

    public void setQualificationPeriod(Period qualificationPeriod) {
        this.qualificationPeriod = qualificationPeriod;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public List<ShortListedFirm> getShortlistedFirms() {
        return shortlistedFirms;
    }

    public void setShortlistedFirms(List<ShortListedFirm> shortlistedFirms) {
        this.shortlistedFirms = shortlistedFirms;
    }

    public String getStage2TenderID() {
        return stage2TenderID;
    }

    public void setStage2TenderID(String stage2TenderID) {
        this.stage2TenderID = stage2TenderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSubmissionMethod() {
        return submissionMethod;
    }

    public void setSubmissionMethod(List<String> submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public String getSubmissionMethodDetails() {
        return submissionMethodDetails;
    }

    public void setSubmissionMethodDetails(String submissionMethodDetails) {
        this.submissionMethodDetails = submissionMethodDetails;
    }

    public String getTenderID() {
        return tenderID;
    }

    public void setTenderID(String tenderID) {
        this.tenderID = tenderID;
    }

    public Period getTenderPeriod() {
        return tenderPeriod;
    }

    public String getTenderStartDate() {
        return tenderPeriod == null ? null : tenderPeriod.getStartDate();
    }

    public String getTenderEndDate() {
        return tenderPeriod == null ? null : tenderPeriod.getEndDate();
    }

    public void setTenderPeriod(Period tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public List<Tenderer> getTenderers() {
        return tenderers;
    }

    public void setTenderers(List<Tenderer> tenderers) {
        this.tenderers = tenderers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Value getValue() {
        return value;
    }

    public Double getValueAmount() {
        return value == null ? null : value.getAmount();
    }

    public String getValueCurrency() {
        return value == null ? null : value.getCurrency();
    }

    public Boolean getValueAddedTaxIncluded() {
        return value == null ? null : value.getValueAddedTaxIncluded();
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
