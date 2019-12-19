package com.datapath.release.loader.containers;

public class ContactPoint {

    private String availableLanguage;
    private String email;
    private String faxNumber;
    private String name;
    private String telephone;
    private String url;

    public String getAvailableLanguage() {
        return availableLanguage;
    }

    public void setAvailableLanguage(String availableLanguage) {
        this.availableLanguage = availableLanguage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
