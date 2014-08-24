package com.tleaf.lifelog.dto;

import java.util.Map;

/**
 * Created by jangyoungjin on 8/23/14.
 */
public class Location extends Lifelog {
    
    @Override
    public void setMap(Map<String, Object> map) {
        // TODO Auto-generated method stub
        map.put("longitude", super.getLongitude());
        map.put("latitude", super.getLatitude());
        map.put("locationtime", super.getLocationTime());
        map.put("type", super.getType());
        map.put("logtime", super.getLogTime());
    }

}
