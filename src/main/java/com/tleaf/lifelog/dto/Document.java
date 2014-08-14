package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by jangyoungjin on 8/7/14.
 */
public class Document {
    @JsonProperty
    private String id;

    @JsonProperty
    private String rev;

    private String type;

    private String Date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
