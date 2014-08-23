package com.tleaf.lifelog.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tleaf.lifelog.dto.Bookmark;
import com.tleaf.lifelog.dto.Document;
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
     * 14/08/07 By 장영진
     * 해당 사용자의 전체 라이프로그를 가져온다
     *
     * @param userid : 사용자식별자
     * @return : 제이슨포멧 스트링
     * @throws Exception
     */
    @Override
    public ArrayList<String> getAllUserLifelog(String userid) throws Exception {
        CouchDbConnector db = couchDbConn.getCouchDbConnetor(userid);
        db.createDatabaseIfNotExists();
        ArrayList<String> data = new ArrayList<String>();

        ViewQuery query = new ViewQuery()
                .designDocId("_design/user")
                .viewName("lifelogs")
                .key(userid);

        ViewResult result = db.queryView(query);
        Iterator<ViewResult.Row> iterator = result.iterator();
        while (iterator.hasNext()) {
            ViewResult.Row row = iterator.next();
            System.out.println(row.getValue());
            data.add(row.getValue());
        }
        return data;
    }


    /**
     * 14/08/07 By 장영진
     * 해당사용자의 북마크 로그를 가져온다.
     *
     * @param userid : 사용자 식별자
     * @return : 리스트형식
     * @throws Exception
     */
    @Override
    public ArrayList<Document> getUserLifelof(String userid, String lifelog) throws Exception {
        CouchDbConnector db = couchDbConn.getCouchDbConnetor(userid);
        db.createDatabaseIfNotExists();
        ArrayList<Document> data = new ArrayList<Document>();

        ViewQuery query = new ViewQuery()
                .designDocId("_design/user")
                .viewName(lifelog);

        ViewResult result = db.queryView(query);
        Iterator<ViewResult.Row> iterator = result.iterator();
        while (iterator.hasNext()) {
            ViewResult.Row row = iterator.next();
            JsonNode jsonNode = row.getValueAsNode();
            String type = jsonNode.get("type").asText();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (type.equals("bookmark")) {
                Bookmark bookmark = gson.fromJson(row.getValue(), Bookmark.class);
                data.add(bookmark);
            }
        }

        return data;
    }

    public boolean initUserDatabase(String dbName) throws Exception {

        CouchDbConnector db = couchDbConn.getCouchDbConnetor(dbName);

        CouchDbInstance dbInstance = couchDbConn.getCouchDbInstance();

        ReplicationCommand rpcmd = new ReplicationCommand.Builder().source(dbName).target("http://couchdb:dudwls@54.191.147.237:5984/tleafall").continuous(true).build();

        db.createDatabaseIfNotExists();

        db.replicateFrom("http://couchdb:dudwls@54.191.147.237:5984/designdocdb");
        db.replicateFrom("http://couchdb:dudwls@54.191.147.237:5984/metadata");

//        db.replicateTo("http://couchdb:dudwls@54.191.147.237:5984/tleafall");
        dbInstance.replicate(rpcmd);


        return true;

    }

}

