package com.tleaf.lifelog.controller;

import com.tleaf.lifelog.service.ApiService;
import com.tleaf.lifelog.service.ResourceCreator;
import com.wordnik.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
    @RequestMapping(value = "{dataName}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserBookmarks(@RequestParam(value = "userid", required = true) String userId,
                                                @PathVariable(value = "dataName") String dataName) {
        System.out.println(dataName);
        Map<String, Object> result = null;
        result = resourceCreator.createJsonMapData(apiService.getUserLifelog(userId, dataName));
        return result;
    }

    /**
     * 2014.08.06 by young
     * 해당 사용자의 전체 북마크 데이터를 정해진 날짜 만큼 가져온다.
     */
    /*
    @RequestMapping(value = "{userId}/bookmarks/time", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserBookmarksByTime(@PathVariable(value = "userId") String userId,
                                                      @RequestParam("startTime") String startTime) {
        Map<String, Object> result = null;
        result = resourceCreator.createJsonMapData(apiService.getUserBookmarks(userId));
        return result;
    }*/

    /**
     * 2014.08.16 by susu
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
