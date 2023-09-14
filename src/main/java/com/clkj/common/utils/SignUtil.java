package com.clkj.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

/**
 * 签名生成和验证签名
 *
 * @author Created by YangLiu on 2020-11-19
 */
public class SignUtil {

    static final Long EXPIRE_TIME = 30L;

    /**
     * HmacSHA256生成签名
     *
     * @param rawString     原始字符串
     * @param securityToken 加密key
     * @return
     */
    public static String generate(String rawString, String timestamp, String securityToken) {
        HMac hmac = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, securityToken);
        return hmac.digestHex(rawString + timestamp);
    }

    /**
     * HmacSHA256验证签名
     *
     * @param rawString     原始字符串
     * @param securityToken 加密key
     * @param rawSign       原始签名
     * @return
     */
    public static boolean check(String rawString, String timestamp, String securityToken, String rawSign) {
        return generate(rawString, timestamp, securityToken).equals(rawSign);
    }

    /**
     * 验证时间戳是否过期
     *
     * @param timestamp 时间戳
     * @return true：过期，false：有效
     */
    public static boolean expire(String timestamp) {
        Long time = new Long(timestamp);
        Long cur = System.currentTimeMillis();
        return time > cur || cur - time > EXPIRE_TIME * 1000;
    }

}
