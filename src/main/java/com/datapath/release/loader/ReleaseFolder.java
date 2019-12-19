package com.datapath.release.loader;

import java.time.LocalDate;

public class ReleaseFolder {

    private String name;
    private String address;
    private LocalDate date;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}