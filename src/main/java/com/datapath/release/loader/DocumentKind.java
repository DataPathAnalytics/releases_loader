package com.datapath.release.loader;

public enum DocumentKind {
    BID(1),
    TENDER(2),
    AWARD(3),
    AWARD_COMPLAINT(4),
    CONTRACT(5),
    BID_ELIGIBILITY(6),
    ;

    private int id;

    DocumentKind(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
