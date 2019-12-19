package com.datapath.release.loader.containers;

import java.util.List;

public class Buyer {

    private List<ContactPoint> additionalContactPoints;
    private Address address;
    private Identifier identifier;
    private ContactPoint contactPoint;
    private String name;
    private String kind;

    public List<ContactPoint> getAdditionalContactPoints() {
        return additionalContactPoints;
    }

    public void setAdditionalContactPoints(List<ContactPoint> additionalContactPoints) {
        this.additionalContactPoints = additionalContactPoints;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public ContactPoint getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(ContactPoint contactPoint) {
        this.contactPoint = contactPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
