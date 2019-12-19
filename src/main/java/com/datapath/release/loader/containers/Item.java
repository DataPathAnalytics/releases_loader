package com.datapath.release.loader.containers;

import java.util.List;

public class Item {

    private List<Classification> additionalClassifications;
    private Classification classification;
    private DeliveryAddress deliveryAddress;
    private Period deliveryDate;
    private String description;
    private String id;
    private Double quantity;
    private String relatedLot;
    private Unit unit;

    public List<Classification> getAdditionalClassifications() {
        return additionalClassifications;
    }

    public void setAdditionalClassifications(List<Classification> additionalClassifications) {
        this.additionalClassifications = additionalClassifications;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Period getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Period deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getRelatedLot() {
        return relatedLot;
    }

    public void setRelatedLot(String relatedLot) {
        this.relatedLot = relatedLot;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
