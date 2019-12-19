package com.datapath.release.loader.containers;

public class DeliveryAddress {

    private String countryName;
    private String countryName_en;
    private String countryName_ru;
    private String locality;
    private String postalCode;
    private String region;
    private String streetAddress;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName_en() {
        return countryName_en;
    }

    public void setCountryName_en(String countryName_en) {
        this.countryName_en = countryName_en;
    }

    public String getCountryName_ru() {
        return countryName_ru;
    }

    public void setCountryName_ru(String countryName_ru) {
        this.countryName_ru = countryName_ru;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
