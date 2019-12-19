package com.datapath.release.loader.query;

public class Query {

    public static SelectQuery select() {
        return new SelectQuery();
    }

    public static InsertQuery insert() {
        return new InsertQuery();
    }

    public static UpdateQuery update() {
        return new UpdateQuery();
    }

}