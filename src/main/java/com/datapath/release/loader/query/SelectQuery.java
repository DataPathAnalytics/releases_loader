package com.datapath.release.loader.query;

import org.springframework.util.StringUtils;

import static com.datapath.release.loader.query.QueryConsts.*;

public class SelectQuery {

    private StringBuilder query;

    public SelectQuery() {
        query = new StringBuilder();
        query.append(SELECT);
    }

    public SelectQuery columns(String... columns) {
        query.append(StringUtils.arrayToCommaDelimitedString(columns));
        return this;
    }

    public SelectQuery from(String table) {
        query.append(FROM);
        query.append(table);
        return this;
    }

    public SelectQuery where() {
        query.append(WHERE);
        return this;
    }

    public SelectQuery condition(String column, String operator, String value) {
        query.append(column);
        query.append(operator);
        query.append(QueryConverter.convert(value));
        return this;
    }

    public SelectQuery condition(String column, String operator, Number value) {
        query.append(column);
        query.append(operator);
        query.append(QueryConverter.convert(value));
        return this;
    }

    public SelectQuery and() {
        query.append(AND);
        return this;
    }

    public SelectQuery exists(SelectQuery selectQuery) {
        query.append(EXISTS);
        query.append(LEFT_PARENTHESIS);
        query.append(selectQuery.build());
        query.append(RIGHT_PARENTHESIS);
        return this;
    }

    public String build() {
        return query.toString();
    }

}
