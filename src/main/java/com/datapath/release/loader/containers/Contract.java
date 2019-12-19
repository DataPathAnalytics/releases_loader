package com.datapath.release.loader.containers;

import java.util.List;

public class Contract {

    private String awardID;
    private String contractID;
    private String contractNumber;
    private String dateSigned;
    private String description;
    private List<Document> documents;
    private String id;
    private List<Item> items;
    private Period period;
    private String status;
    private List<Tenderer> suppliers;
    private String title;
    private Value value;

    public String getAwardID() {
        return awardID;
    }

    public void setAwardID(String awardID) {
        this.awardID = awardID;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(String dateSigned) {
        this.dateSigned = dateSigned;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tenderer> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Tenderer> suppliers) {
        this.suppliers = suppliers;
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
