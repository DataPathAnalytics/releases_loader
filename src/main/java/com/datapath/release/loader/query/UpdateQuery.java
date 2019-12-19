package com.datapath.release.loader.query;

import java.util.ArrayList;
import java.util.List;

import static com.datapath.release.loader.query.QueryConsts.UPDATE;
import static com.datapath.release.loader.query.QueryConsts.WHERE;
import static com.datapath.release.loader.query.QueryConverter.convert;
import static org.springframework.util.StringUtils.collectionToCommaDelimitedString;

public class UpdateQuery {


    private StringBuilder query;
    private String table;
    private List<String> columns;

    public UpdateQuery() {
        columns = new ArrayList<>();
        query = new StringBuilder();
    }

    public UpdateQuery table(String table) {
        this.table = table;
        return this;
    }

    public UpdateQuery column(String column, Boolean value) {
        columns.add(column + " = " + convert(value));
        return this;
    }

    public UpdateQuery column(String column, String value) {
        columns.add(column + " = " + convert(value));
        return this;
    }

    public UpdateQuery column(String column, Number value) {
        columns.add(column + " = " + convert(value));
        return this;
    }

    public UpdateQuery where() {
        query.append(WHERE);
        return this;
    }

    public UpdateQuery condition(String column, String operator, String value) {
        query.append(column);
        query.append(operator);
        query.append(convert(value));
        return this;
    }

    public UpdateQuery condition(String column, String operator, Number value) {
        query.append(column);
        query.append(operator);
        query.append(convert(value));
        return this;
    }

    public UpdateQuery and() {
        query.append(QueryConsts.AND);
        return this;
    }


    public UpdateQuery returning(String column) {
        query.append(QueryConsts.RETURNING);
        query.append(column);
        return this;
    }

    public String build() {
        query.insert(0, collectionToCommaDelimitedString(columns));
        query.insert(0, " SET ");
        query.insert(0, table == null ? "" : table + " ");
        query.insert(0, UPDATE);
        return query.toString();
    }

}
