package com.tleaf.lifelog.dao;

import com.tleaf.lifelog.dto.Bookmark;
import com.tleaf.lifelog.dto.Document;
import org.codehaus.jackson.JsonNode;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;

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
        CouchDbConnector db = couchDbConn.getCouchDbConnetor("richard");
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
    public ArrayList<Document> getUserBookmarks(String userid) throws Exception {
        CouchDbConnector db = couchDbConn.getCouchDbConnetor("richard");
        db.createDatabaseIfNotExists();
        ArrayList<Document> data = new ArrayList<Document>();

        ViewQuery query = new ViewQuery()
                .designDocId("_design/user")
                .viewName("bookmarks")
                .key(userid);

        ViewResult result = db.queryView(query);
        Iterator<ViewResult.Row> iterator = result.iterator();
        while (iterator.hasNext()) {
            ViewResult.Row row = iterator.next();
            JsonNode jsonNode = row.getValueAsNode();
            System.out.println(row.getValue());

            //북마크 객체를 생성하고 받아온 자료를 셋팅해준다.
            Bookmark bookmark = new Bookmark();
            bookmark.setTitle(jsonNode.get("title").asText());
            //bookmark.setsiteUrl(jsonNode.get("site_url").asText());
            bookmark.setMemo(jsonNode.get("memo").asText());
            bookmark.setType(jsonNode.get("type").asText());
            bookmark.setDate(jsonNode.get("date").asText());

            data.add(bookmark);
        }

        return data;
    }


}

