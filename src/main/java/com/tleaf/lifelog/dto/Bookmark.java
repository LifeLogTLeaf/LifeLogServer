package com.tleaf.lifelog.dto;

import java.util.Map;

/**
 * Created by jangyoungjin on 8/23/14.
 */
public class Bookmark extends Lifelog {
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteUrl() {
        return url;
    }

    public void setSiteUrl(String rrl) {
        this.url = url;
    }

}
