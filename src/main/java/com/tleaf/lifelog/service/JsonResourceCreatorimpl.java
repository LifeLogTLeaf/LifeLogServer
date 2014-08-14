package com.tleaf.lifelog.service;

import com.tleaf.lifelog.dto.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jangyoungjin on 8/7/14.
 */
public class JsonResourceCreatorimpl implements ResourceCreator {
    private final String VERSION = "v1.0";
    private String errorCode;

    /**
     * json포멧을 따르는 문자열을 리턴한다.
     *
     * @param data : 생성된 데이터 셋
     * @return : 생성된 문자열
     */
    @Override
    public String createJsonStringData(ArrayList<String> data) {
        String result = "";
        result += "{" + "\"version\"" + ":" + "\"" + VERSION + "\",";
        result += "\"count\"" + ":" + "\"" + data.size() + "\",";
        result += "\"data\"" + ":" + "[";
        for (int i = 0; i < data.size(); ++i) {
            result += data.get(i);
            if (i + 1 != data.size()) {
                result += ",";
            }
        }
        result += " ] ";
        result += " } ";
        return result;
    }

    @Override
    public Map<String, Object> createJsonMapData(ArrayList<Document> data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("version", VERSION);
        result.put("count", data.size());
        result.put("data", data);
        return result;
    }

    public String createErrorJsonData(){
        String result = "";

        return result;
    }
}
