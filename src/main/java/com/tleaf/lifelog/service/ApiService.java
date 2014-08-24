package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dto.Lifelog;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 7/27/14.
 */
public interface ApiService {
    public ArrayList<Lifelog> getUserLifelog(String userid, String lifelog);

    public String initUserDatabase(String dbName);
}