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
            System.out.println(row.getValue());
            if( jsonNode.get("logType") !=null){
                String type = jsonNode.get("logType").asText();
                if (type != null){
                    addDataToArray(data, type, row);
                }
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


    public boolean initUserDatabase(String dbName) throws Exception {

        CouchDbInstance dbInstance = couchDbConn.getCouchDbInstance();
        if (dbInstance.checkIfDbExists(DbPath.fromString(dbName))) return false;

        /*
        Edited by Susu, 2014.8.21
        Added Checking Methods
         */

        CouchDbConnector db = couchDbConn.getCouchDbConnetor(dbName);

        db.createDatabaseIfNotExists();
        db.replicateFrom("http://couchdb:dudwls@54.191.147.237:5984/designdocdb");
        db.replicateFrom("http://couchdb:dudwls@54.191.147.237:5984/metadata");
//     +userInfo
        ReplicationCommand rpcmd =
                new ReplicationCommand.Builder().source(dbName).target("http://couchdb:dudwls@54.191.147.237:5984/tleafall")
                        .continuous(true).build();
        dbInstance.replicate(rpcmd);

        return true;

    }

}

