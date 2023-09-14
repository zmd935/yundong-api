package com.clkj.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.clkj.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Created by YangLiu on 2021-04-02
 */
@Slf4j
public class HttpUtils {

    public static String get(String url, Map<String, Object> paramMap, Map<String, String> headerMap) {
        String path = HttpUtil.toParams(paramMap);
        log.info("request path:" + path);
        HttpRequest http = HttpUtil.createGet(url + "?" + path);

        http.disableCache();
        http.disableCookie();
        if (headerMap != null) {
            http.headerMap(headerMap, false);
        }
        http.timeout(30000);

        try {
            HttpResponse response = http.execute();
            if (response.isOk()) {
                log.info("success body:" + response.body());
            } else {
                log.info("status:" + response.getStatus());
                log.info("body:" + response.body());
            }

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException(e.getMessage());
        }

    }

    public static String post(String url, Map<String, Object> paramMap, Map<String, String> headerMap) {
        HttpRequest http = HttpUtil.createPost(url);
        http.disableCache();
        http.disableCookie();

        http.timeout(30000);
        if (paramMap != null) {
            http.body(JSONObject.toJSONString(paramMap));
        }
        if (headerMap != null) {
            http.headerMap(headerMap, false);
        }

        try {
            log.info("request body:" + JSONObject.toJSONString(paramMap));
            HttpResponse response = http.execute();
            if (response.isOk()) {
                log.info("success body:" + response.body());
            } else {
                log.info("status:" + response.getStatus());
                log.info("body:" + response.body());
            }
            return response.body();
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }
}
