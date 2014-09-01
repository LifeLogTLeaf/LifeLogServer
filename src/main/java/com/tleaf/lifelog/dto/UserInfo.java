package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 8/23/14.
 */

public class UserInfo {
    @JsonProperty("_id")
    private String id;
    private String gender;
    private ArrayList<Device> deviceArrayList;
    private FacebookUserInfo userFacebookInfo;

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public FacebookUserInfo getUserFacebookUserInfo() {
        return userFacebookInfo;
    }

    public void setUserFacebookUserInfo(FacebookUserInfo userFacebookUserInfo) {
        this.userFacebookInfo = userFacebookUserInfo;
    }

    public ArrayList<Device> getDeviceArrayList() {
        return deviceArrayList;
    }

    public void setDeviceArrayList(ArrayList<Device> deviceArrayList) {
        this.deviceArrayList = deviceArrayList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

