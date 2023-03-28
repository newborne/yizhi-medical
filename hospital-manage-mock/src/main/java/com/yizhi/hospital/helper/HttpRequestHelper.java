package com.yizhi.hospital.helper;

import com.alibaba.fastjson.JSONObject;
import com.yizhi.hospital.util.HttpUtil;
import com.yizhi.hospital.util.MD5;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Http request helper.
 */
@Slf4j
public class HttpRequestHelper {
    //private final static String signKey = "09c1ff67d1ae4999e137f34b0dff1046";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("d", "4");
        paramMap.put("b", "2");
        paramMap.put("c", "3");
        paramMap.put("a", "1");
        log.info(getSign(paramMap, ""));
    }

    /**
     * Switch map map.
     *
     * @param paramMap the param map
     * @return the map
     */
    public static Map<String, Object> switchMap(Map<String, String[]> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            resultMap.put(param.getKey(), param.getValue()[0]);
        }
        return resultMap;
    }

    /**
     * Gets sign.
     *
     * @param paramMap the param map
     * @param signKey  the sign key
     * @return the sign
     */
    public static String getSign(Map<String, Object> paramMap, String signKey) {
        if (paramMap.containsKey("sign")) {
            paramMap.remove("sign");
        }
        TreeMap<String, Object> sorted = new TreeMap<>(paramMap);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Object> param : sorted.entrySet()) {
            str.append(param.getValue()).append("|");
        }
        str.append(signKey);
        log.info("加密前：" + str.toString());
        String md5Str = MD5.encrypt(str.toString());
        log.info("加密后：" + md5Str);
        return md5Str;
    }

    /**
     * Is sign equals boolean.
     *
     * @param paramMap the param map
     * @param signKey  the sign key
     * @return the boolean
     */
    public static boolean isSignEquals(Map<String, Object> paramMap, String signKey) {
        String sign = (String) paramMap.get("sign");
        String md5Str = getSign(paramMap, signKey);
        if (!sign.equals(md5Str)) {
            return false;
        }
        return true;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public static long getTimestamp() {
        return new Date().getTime();
    }

    /**
     * Send request json object.
     *
     * @param paramMap the param map
     * @param url      the url
     * @return the json object
     */
    public static JSONObject sendRequest(Map<String, Object> paramMap, String url) {
        String result = "";
        try {
            //封装post参数
            StringBuilder postdata = new StringBuilder();
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {
                postdata.append(param.getKey()).append("=")
                        .append(param.getValue()).append("&");
            }
            log.info(String.format("--> 发送请求：post data %1s", postdata));
            byte[] reqData = postdata.toString().getBytes("utf-8");
            byte[] respdata = HttpUtil.doPost(url, reqData);
            result = new String(respdata);
            log.info(String.format("--> 应答结果：result data %1s", result));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }
}
