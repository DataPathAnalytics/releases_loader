package com.datapath.release.loader.query;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.datapath.release.loader.query.QueryConsts.*;
import static com.datapath.release.loader.query.QueryConverter.convert;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.collectionToCommaDelimitedString;

public class InsertQuery {

    private StringBuilder query;
    private Map<String, String> columns;
    private String returningColumn;

    public InsertQuery() {
        query = new StringBuilder();
        query.append(INSERT);
        columns = new LinkedHashMap<>();
    }

    public InsertQuery into(String table) {
        query.append(INTO);
        query.append(table);
        return this;
    }

    public InsertQuery column(String name, String value) {
        columns.put(name, convert(value));
        return this;
    }

    public InsertQuery column(String name, Boolean value) {
        columns.put(name, convert(value));
        return this;
    }

    public InsertQuery column(String name, Number value) {
        columns.put(name, convert(value));
        return this;
    }

    public InsertQuery returning(String column) {
        returningColumn = column;
        return this;
    }

    public String build() {
        query.append(LEFT_PARENTHESIS);
        query.append(collectionToCommaDelimitedString(columns.keySet()));
        query.append(RIGHT_PARENTHESIS);
        query.append(VALUES);
        query.append(LEFT_PARENTHESIS);
        query.append(collectionToCommaDelimitedString(columns.values()));
        query.append(RIGHT_PARENTHESIS);
        if (nonNull(returningColumn)) {
            query.append(RETURNING).append(returningColumn);
        }
        return query.toString();
    }

}
