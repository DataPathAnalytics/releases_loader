package com.datapath.release.loader;

public enum ComplaintKind {
    TENDER(1),
    AWARD(2);

    private Integer id;

    ComplaintKind(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
