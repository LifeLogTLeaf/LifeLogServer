package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dao.ApiDao;
import com.tleaf.lifelog.dto.Document;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by jangyoungjin on 8/6/14.
 */
public class ApiServiceImple implements ApiService {

    @Inject
    private ApiDao apiDao;

    @Override
    public ArrayList<String> getAllUserLifelog(String userid) {
        ArrayList<String> data = null;
        try {
            data = apiDao.getAllUserLifelog(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public ArrayList<Document> getUserLifelog(String userid, String lifelog) {
        ArrayList<Document> data = null;

        try {
            data = apiDao.getUserLifelof(userid, lifelog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String initUserDatabase(String dbName)
    {
        try {
            apiDao.initUserDatabase(dbName);
            return "Init Complete";
        } catch (Exception e) {
            e.printStackTrace();
            return "Init Failed";
        }
    }
}
