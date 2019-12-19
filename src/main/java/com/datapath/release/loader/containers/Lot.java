package com.datapath.release.loader.containers;

public class Lot {

    private String description;
    private Guarantee guarantee;
    private String id;
    private Boolean pendingCancellation;
    private String status;
    private String title;
    private Value value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Guarantee getGuarantee() {
        return guarantee;
    }

    public Long getGuaranteeAmount() {
        return guarantee == null ? null  : guarantee.getAmount();
    }

    public String getGuaranteeCurrency() {
        return guarantee == null ? null  : guarantee.getCurrency();
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

    public Boolean getPendingCancellation() {
        return pendingCancellation;
    }

    public void setPendingCancellation(Boolean pendingCancellation) {
        this.pendingCancellation = pendingCancellation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
