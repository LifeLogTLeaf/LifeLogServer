package com.tleaf.lifelog.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jangyoungjin on 8/7/14.
 */
public class Bookmark extends Document {
    private String title;

    private String siteUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getsiteUrl() {
        return siteUrl;
    }

    public void setsiteUrl(String site_url) {
        this.siteUrl = site_url;
    }

}
