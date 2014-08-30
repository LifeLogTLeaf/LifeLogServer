package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;

/**
 * Created by jangyoungjin on 8/23/14.
 */

public abstract class Lifelog {
    @JsonProperty("_id")
    protected String id;

    @JsonProperty("_rev")
    protected String rev;

    private double latitude;
    private double longitude;
    private long locationTime;
    private String logType;

    private long logTime;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(long locationTime) {
        this.locationTime = locationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public long getLogTime() {
        return logTime;
    }

    public void setLogTime(long logTime) {
        this.logTime = logTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
