package com.tleaf.lifelog.controller;

import com.tleaf.lifelog.dao.ApiDaoImple;
import com.tleaf.lifelog.dto.Bookmark;
import com.tleaf.lifelog.service.ApiService;
import com.tleaf.lifelog.service.ResourceCreator;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.ektorp.CouchDbConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 2014.08.06 by jYoung
 * RESTful API Controller
 */
@Api(value = "api", description = "REST API를 호출할때 도멘인 바로앞에 붙여주세요.")
@RequestMapping(value = "api/*")
@Controller
public class ApiController {

    @Inject
    private ApiService apiService;

    @Inject
    private ResourceCreator resourceCreator;

    /**
     * 2014.08.06 by young
     * 해당 사용자의 전체 라이프로그를 가져온다.
     */
    @RequestMapping(value = "{userId}/lifelogs", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUserLifelog(@PathVariable(value = "userId") String userId) {
        String result;
        result = resourceCreator.createJsonStringData(apiService.getAllUserLifelog(userId));
        return result;
    }

    /**
     * 2014.08.06 by young
     * 해당 사용자의 전체 북마크 데이터를 가져온다.
     */
    @ApiOperation(value = "GET user's all bookmark",
                  notes = "GET user's all bookmark by user identification",
                  response = Bookmark.class)
    @RequestMapping(value = "{userId}/bookmarks", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserBookmarks(@PathVariable(value = "userId") String userId) {
        Map<String, Object> result = null;
        result = resourceCreator.createJsonMapData(apiService.getUserBookmarks(userId));
        return result;
    }

    /**
     * 2014.08.06 by young
     * 해당 사용자의 전체 북마크 데이터를 정해진 날짜 만큼 가져온다.
     */
    @RequestMapping(value = "{userId}/bookmarks/time", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserBookmarksByTime(@PathVariable(value = "userId") String userId,
                                                      @RequestParam("startTime") String startTime) {
        Map<String, Object> result = null;
        result = resourceCreator.createJsonMapData(apiService.getUserBookmarks(userId));
        return result;
    }

    /**
     * 2014.08.16 by susu
     * @param dbName
     * @param model
     * @return
     */
    @RequestMapping(value = "{dbName}/init")
    @ResponseBody
    public Map<String,String> initUserDatabase(@PathVariable(value = "dbName") String dbName)
    {
        Map<String,String> result = new HashMap<String, String>();
        result.put( "response", apiService.initUserDatabase(dbName) );
        return result;
    }

}
