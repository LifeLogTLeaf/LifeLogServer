package com.tleaf.lifelog.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by jangyoungjin on 8/23/14.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo{
    private String userName;
    private String gender;
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
}

