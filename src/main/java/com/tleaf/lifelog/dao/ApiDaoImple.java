package com.tleaf.lifelog.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tleaf.lifelog.dto.*;
import org.codehaus.jackson.JsonNode;
import org.ektorp.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jangyoungjin on 7/26/14.
 */

public class ApiDaoImple implements ApiDao {

    @Inject
    private CouchDbConn couchDbConn;

    /**
     * 2014.08.24 사용자의 라이프로그를 가져온다.
     *
     * @param userid  : 사용자 아이디
     * @param lifelog : 가져오고자하는 로그 ( lifelogs로 들어오면 전체데이터이다. )
     * @return        : ArrayList형식으로 리턴한다.
     * @throws Exception
     */
    @Override
    public ArrayList<Lifelog> getUserLifelog(String userid, String lifelog) throws Exception {
        CouchDbConnector db = couchDbConn.getCouchDbConnetor(userid);
        db.createDatabaseIfNotExists();
        ArrayList<Lifelog> data = new ArrayList<Lifelog>();

        ViewQuery query = new ViewQuery()
                .designDocId("_design/user")
                .viewName(lifelog)
                .descending(true);
                //.limit(10);

        ViewResult result = db.queryView(query);
        Iterator<ViewResult.Row> iterator = result.iterator();
        while (iterator.hasNext()) {
            ViewResult.Row row = iterator.next();
            JsonNode jsonNode = row.getValueAsNode();
            String type = jsonNode.get("type").asText();

            System.out.println(row.getValue());

            if (type != null){
                addDataToArray(data, type, row);
            }


        }

        return data;
    }

    /* 타입에 맞게 배열에 데이터를 저장합니다. */
    private void addDataToArray(ArrayList<Lifelog> data, String type, ViewResult.Row row){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (type.equals("bookmark")) {
            Bookmark bookmark = gson.fromJson(row.getValue(), Bookmark.class);
            bookmark.setId(row.getValueAsNode().get("_id").asText());
            bookmark.setRev(row.getValueAsNode().get("_rev").asText());
            data.add(bookmark);
        } else if (type.equals("sms")) {
            Sms sms = gson.fromJson(row.getValue(), Sms.class);
            sms.setId(row.getValueAsNode().get("_id").asText());
            sms.setRev(row.getValueAsNode().get("_rev").asText());
            data.add(sms);
        } else if (type.equals("call")) {
            Call call = gson.fromJson(row.getValue(), Call.class);
            call.setId(row.getValueAsNode().get("_id").asText());
            call.setRev(row.getValueAsNode().get("_rev").asText());
            data.add(call);
        } else if (type.equals("location")) {
            Location location = gson.fromJson(row.getValue(), Location.class);
            location.setId(row.getValueAsNode().get("_id").asText());
            location.setRev(row.getValueAsNode().get("_rev").asText());
            data.add(location);
        } else if (type.equals("photo")) {
            Photo photo = gson.fromJson(row.getValue(), Photo.class);
            photo.setId(row.getValueAsNode().get("_id").asText());
            photo.setRev(row.getValueAsNode().get("_rev").asText());
            data.add(photo);
        }
    }


    public String initUserDatabase(UserInfo userInfo) throws Exception {

        /*
        Edited by Susu, 2014.8.21 , 2014.8.26
        Added Checking Methods
        Checking http Request with DeviceId
         */

        String dbName = userInfo.getId();
        Device device = userInfo.getDeviceArrayList().get(0);

        // Check if Username is Already Taken
        CouchDbInstance dbInstance = couchDbConn.getCouchDbInstance();
        if ( dbInstance.checkIfDbExists(DbPath.fromString(dbName)) ) return "Username Already Exists";

        // Check if Device is Registered
        CouchDbConnector devicedb = couchDbConn.getCouchDbConnetor("devices");
        Device checkDevice = devicedb.find( Device.class , device.getId() );

        if( checkDevice != null ) return "Device is Already Registered in TLeaf";

        // Create User Database and
        // 1. Import Design Document  2. Register DeviceId  3. Register User  4. set Continous Replication to tleafall

        // 1.
        CouchDbConnector db;
        try{
            db = couchDbConn.getCouchDbConnetor(dbName);
        }catch ( Exception e )
        {
            e.printStackTrace();
            return "Only lowercase characters (a-z), digits (0-9), and any of the characters _, $, (, ), +, -, and / are allowed. Must begin with a letter.";
        }
        db.createDatabaseIfNotExists();
        db.replicateFrom("http://couchdb:dudwls@54.191.147.237:5984//designdocdb");

        // 2.
        devicedb.create( device.getId() , device );

        // 3.
        CouchDbConnector user = couchDbConn.getCouchDbConnetor("userdb");
        user.create( userInfo.getId(), userInfo );

        // 4.
        ReplicationCommand rpcmd =
                new ReplicationCommand.Builder().source(dbName).target("http://couchdb:dudwls@54.191.147.237:5984//tleafall")
                        .continuous(true).build();
        dbInstance.replicate(rpcmd);

        return "User Database Created";

    }

}