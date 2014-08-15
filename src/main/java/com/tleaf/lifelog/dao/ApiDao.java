package com.tleaf.lifelog.dao;

import com.tleaf.lifelog.dto.Document;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 7/27/14.
 */
public interface ApiDao {
    public ArrayList<String> getAllUserLifelog(String userid) throws Exception;
    public ArrayList<Document> getUserBookmarks(String userid) throws Exception;
    public boolean initUserDatabase(String dbName) throws Exception;
}

