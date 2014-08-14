package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dto.Document;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 7/27/14.
 */
public interface ApiService {
    public ArrayList<String> getAllUserLifelog(String userid);
    public ArrayList<Document> getUserBookmarks(String userid);
}
