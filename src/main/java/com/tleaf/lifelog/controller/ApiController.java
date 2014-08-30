package com.tleaf.lifelog.controller;

import com.google.gson.Gson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tleaf.lifelog.dto.UserInfo;
import com.tleaf.lifelog.service.ApiService;
import com.tleaf.lifelog.service.ResourceCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 2014.08.06 by jYoung
 * RESTful API Controller
 */

@RequestMapping(value = "api/*")
@Controller
public class ApiController {

    @Inject
    private ApiService apiService;

    @Inject
    private ResourceCreator resourceCreator;


    /**
     * 2014.08.24 by young
     * 해당 사용자의 전체 북마크 데이터를 가져온다.
     */
    @RequestMapping(value = "{dataName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserBookmarks(@RequestParam(value = "userid", required = true) String userId,
                                                @PathVariable(value = "dataName") String dataName) {
        //CORS 규약에 맞게 Response에 해당 규약을 넣는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        Map<String, Object> result = null;
        result = resourceCreator.createJsonMapData(apiService.getUserLifelog(userId, dataName));
        ResponseEntity<Map<String, Object>> entity = new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
        return entity;
    }

    /**
     * 2014.08.27 by young
     * 페이스북 로그인로직에 해당하는 컨트롤러입니다.
     */
    @RequestMapping(value = "facebooklogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> setFacebookUserinfor(@RequestBody String jsonString) {
        //CORS 규약에 맞게 Response에 해당 규약을 넣는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(jsonString, UserInfo.class);
        String result = apiService.initFacebookUser(userInfo);

        //System.out.println(userInfo.getUserId());

        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("result", result);
        ResponseEntity<Map<String, Object>> entity = new ResponseEntity<Map<String, Object>>(mapResult, headers, HttpStatus.OK);
        return entity;
    }


    /**
     * 2014.08.16 by susu
     * 2014.08.26 bu susu
     * edited init method with user object
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> initUserDatabase(@RequestParam(value = "userinfo" , required = true) String userinfo) {
        Map<String, String> result = new HashMap<String, String>();

        UserInfo userInfo = new UserInfo();
        ObjectMapper mapper = new ObjectMapper();
        try {
            userInfo = mapper.readValue(userinfo, new TypeReference<UserInfo>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.put("response", apiService.initUserDatabase( userInfo ));
//          result.put("response", userinfo);
        return result;
    }


}
