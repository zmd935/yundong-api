package com.clkj.common.utils;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Created by YangLiu on 2023-02-01
 */
public class SignMd5Util {
    /**
     * 生成签名
     *
     * @param obj obj
     * @return 签名
     */
    public static String getSign(JSONObject obj, String timestamp, String securityKey) {
        // map转换为自然排序map
        Map<String, Object> params = new TreeMap<>(obj);
        // 签名字段本身不加入
        params.remove("sign");
        params.remove("ts");
        String str = HttpUtil.toParams(params);

        MD5 md5 = MD5.create();
        String rawStr = str + "&securityKey=" + securityKey;

        if (StringUtils.isNotBlank(timestamp)) {
            rawStr = rawStr + "&ts=" + timestamp;
        }
        System.out.println("timestamp：" + timestamp);

        System.out.println("rawStr：" + rawStr);

        String sign = md5.digestHex(rawStr);
        System.out.println("sign：" + sign);
        return sign;
    }

    /**
     * 签名效验
     *
     * @param originalSign originalSign
     * @param obj          obj
     * @return boolean
     */
    public static boolean checkSign(String originalSign, JSONObject obj, String timestamp, String securityKey) {
        return getSign(obj, timestamp, securityKey).equals(originalSign);
    }
}
