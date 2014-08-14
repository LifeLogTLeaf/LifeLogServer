package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dto.Document;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jangyoungjin on 8/7/14.
 */
public interface ResourceCreator {
    String createJsonStringData(ArrayList<String> data);
    Map<String, Object> createJsonMapData(ArrayList<Document> data);

}
