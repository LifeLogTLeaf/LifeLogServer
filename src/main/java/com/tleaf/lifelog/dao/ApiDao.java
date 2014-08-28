package com.tleaf.lifelog.dao;

import com.tleaf.lifelog.dto.Lifelog;
import com.tleaf.lifelog.dto.UserInfo;

import java.util.ArrayList;

/**
 * Created by jangyoungjin on 7/27/14.
 */
public interface ApiDao {
    public ArrayList<Lifelog> getUserLifelog(String userid, String lifelog) throws Exception;
    public String initUserDatabase(UserInfo userInfo) throws Exception;
}

