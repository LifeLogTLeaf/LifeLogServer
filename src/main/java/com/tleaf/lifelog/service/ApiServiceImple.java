package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dao.ApiDao;
import com.tleaf.lifelog.dto.Lifelog;
import com.tleaf.lifelog.dto.UserInfo;

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
    public String initUserDatabase(UserInfo userInfo) {
        /*
        Edited by Susu, 2014.8.21 THU
         */
        try {
            return apiDao.initUserDatabase( userInfo );
        } catch (Exception e) {
            e.printStackTrace();
            return "Init Failed";
        }
    }

    @Override
    public String initFacebookUser(UserInfo userInfo) {
        try {
            apiDao.initUserFacebook( userInfo );
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }
}
