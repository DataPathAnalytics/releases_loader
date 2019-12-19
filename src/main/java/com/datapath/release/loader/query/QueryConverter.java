package com.datapath.release.loader.query;

import static com.datapath.release.loader.query.QueryConsts.*;
import static org.springframework.util.StringUtils.quote;

public class QueryConverter {

    private static String escape(String value) {
        return value.replaceAll(QUOTE, DOUBLE_QUOTE);
    }

    public static String convert(String value) {
        return value == null ? NULL_VALUE : quote(escape(value));
    }

    public static String convert(Boolean value) {
        return value == null ? NULL_VALUE : value.toString();
    }

    public  static String convert(Number value) {
        return value == null ? NULL_VALUE : value.toString();
    }

}
