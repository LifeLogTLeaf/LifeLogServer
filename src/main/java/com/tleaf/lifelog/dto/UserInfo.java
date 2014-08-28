package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 8/23/14.
 */
public class UserInfo {
    @JsonProperty("_id")
    private String userName;
    @JsonProperty("_rev")
    private String rev;

    private String gender;
    private ArrayList<Device> deviceArrayList;
    private FacebookUserInfo userFacebookInfo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
