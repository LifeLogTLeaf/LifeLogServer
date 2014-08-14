package com.tleaf.lifelog.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jangyoungjin on 8/7/14.
 */
@ApiModel( value = "Bookmark", description = "Bookmark resource representation")
public class Bookmark extends Document {
    @ApiModelProperty(value = "Bookmark title", required = true)
    private String title;

    @ApiModelProperty(value = "Bookmark site url", required = true)
    private String siteUrl;

    @ApiModelProperty(value = "Bookmark memo", required = true)
    private String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
