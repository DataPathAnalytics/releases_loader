package com.datapath.release.loader.containers;

public class Tenderer {

    private Address address;
    private ContactPoint contactPoint;
    private Identifier identifier;
    private String name;

    public Address getAddress() {
        return address;
    }

    public String getCountryName() {
        return address == null ? null : address.getCountryName();
    }

    public String getLocality() {
        return address == null ? null : address.getLocality();
    }

    public String getPostalCode() {
        return address == null ? null : address.getPostalCode();
    }

    public String getRegion() {
        return address == null ? null : address.getRegion();
    }

    public String getStreetAddress() {
        return address == null ? null : address.getStreetAddress();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactPoint getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(ContactPoint contactPoint) {
        this.contactPoint = contactPoint;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
