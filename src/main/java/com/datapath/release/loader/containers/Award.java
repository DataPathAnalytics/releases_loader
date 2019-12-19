package com.datapath.release.loader.containers;

import java.util.List;

public class Award {

    private Period complaintPeriod;
    private List<Complaint> complaints;
    private String date;
    private String description;
    private List<Document> documents;
    private Boolean eligible;
    private String id;
    private List<Item> items;
    private String lotID;
    private Boolean qualified;
    private String status;
    private String subcontractingDetails;
    private List<Tenderer> suppliers;
    private String title;
    private Value value;

    public Period getComplaintPeriod() {
        return complaintPeriod;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Boolean getEligible() {
        return eligible;
    }

    public void setEligible(Boolean eligible) {
        this.eligible = eligible;
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

    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
    }

    public Boolean getQualified() {
        return qualified;
    }

    public void setQualified(Boolean qualified) {
        this.qualified = qualified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubcontractingDetails() {
        return subcontractingDetails;
    }

    public void setSubcontractingDetails(String subcontractingDetails) {
        this.subcontractingDetails = subcontractingDetails;
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

    public void setValue(Value value) {
        this.value = value;
    }
}
