package com.datapath.release.loader.containers;

public class Auction {

    private Value minimalStep;
    private Period period;
    private String relatedLot;
    private String url;

    public Value getMinimalStep() {
        return minimalStep;
    }

    public void setMinimalStep(Value minimalStep) {
        this.minimalStep = minimalStep;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getRelatedLot() {
        return relatedLot;
    }

    public void setRelatedLot(String relatedLot) {
        this.relatedLot = relatedLot;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
