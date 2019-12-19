package com.datapath.release.loader.containers;

import java.util.List;

public class Complaint {

    private Boolean acceptance;
    private Tenderer author;
    private String cancellationReason;
    private String date;
    private String dateAnswered;
    private String dateDecision;
    private String dateEscalated;
    private String dateSubmitted;
    private String description;
    private List<Document> documents;
    private String id;
    private String relatedLot;
    private String resolution;
    private String resolutionType;
    private Boolean satisfied;
    private String status;
    private String tendererAction;
    private String tendererActionDate;
    private String title;
    private String type;

    public Boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }

    public Tenderer getAuthor() {
        return author;
    }

    public void setAuthor(Tenderer author) {
        this.author = author;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(String dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public String getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(String dateDecision) {
        this.dateDecision = dateDecision;
    }

    public String getDateEscalated() {
        return dateEscalated;
    }

    public void setDateEscalated(String dateEscalated) {
        this.dateEscalated = dateEscalated;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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

    public String getRelatedLot() {
        return relatedLot;
    }

    public void setRelatedLot(String relatedLot) {
        this.relatedLot = relatedLot;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }

    public Boolean getSatisfied() {
        return satisfied;
    }

    public void setSatisfied(Boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTendererAction() {
        return tendererAction;
    }

    public void setTendererAction(String tendererAction) {
        this.tendererAction = tendererAction;
    }

    public String getTendererActionDate() {
        return tendererActionDate;
    }

    public void setTendererActionDate(String tendererActionDate) {
        this.tendererActionDate = tendererActionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
