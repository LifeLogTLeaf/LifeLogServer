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
    public ArrayList<Document> getUserBookmarks(String userid) {
        ArrayList<Document> data = null;

        try {
            data = apiDao.getUserBookmarks(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String initUserDatabase(String dbName)
    {
        /*
        Edited by Susu, 2014.8.21 THU
         */
        try {
            if( apiDao.initUserDatabase(dbName) ) return "Init Complete";
            else return "Username Already Exists";
        } catch (Exception e) {
            e.printStackTrace();
            return "Init Failed";
        }
    }
}
