package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dao.ApiDao;
import com.tleaf.lifelog.dto.Lifelog;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by jangyoungjin on 8/6/14.
 */
public class ApiServiceImple implements ApiService {

    @Inject
    private ApiDao apiDao;

    @Override
    public ArrayList<Lifelog> getUserLifelog(String userid, String lifelog) {
        ArrayList<Lifelog> data = null;

        try {
            data = apiDao.getUserLifelog(userid, lifelog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public String initUserDatabase(String dbName) {
        /*
        Edited by Susu, 2014.8.21 THU
         */
        try {
            if (apiDao.initUserDatabase(dbName)) return "Init Complete";
            else return "Username Already Exists";
        } catch (Exception e) {
            e.printStackTrace();
            return "Init Failed";
        }
    }
}
