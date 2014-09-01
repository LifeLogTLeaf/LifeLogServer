package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class Device {
    @JsonProperty("_id")
	private String id;
	private String Nickname;


    public String getId() {
        return id;
    }

    public String getNickname() {
        return Nickname;
    }
}
