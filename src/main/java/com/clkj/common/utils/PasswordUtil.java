package com.clkj.common.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 密码加密工具类
 *
 * @author Created by YangLiu on 2020-08-03
 */
public class PasswordUtil {

    /**
     * sha256加密
     *
     * @param password 密码
     * @return
     */
    public static String encrypt(String password) {
        return encrypt(password, "");
    }

    /**
     * sha256加密
     *
     * @param password 密码
     * @param salt
     * @return
     */
    public static String encrypt(String password, String salt) {
        HMac hmac = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, salt);
        return hmac.digestHex(password);
    }

    /**
     * sha256加密
     *
     * @param rawPassword 原密码
     * @param password    密码
     * @return
     */
    public static boolean check(String rawPassword, String salt, String password) {
        return encrypt(rawPassword, salt).equals(password);
    }

    /**
     * len >= 8
     *
     * @param len 长度
     * @return String
     */
    public static String randomPassword(int len) {
        // 首个必须是字母
        String first = RandomStringUtils.randomAlphabetic(1);
        String str = RandomStringUtils.randomAlphanumeric(len - 1);
        // str中随机位置，替换一个随机数字
        String randomNumeric = RandomStringUtils.randomNumeric(1);
        int randomIndex = RandomUtil.randomInt(0, len - 1);
        char[] chars = str.toCharArray();
        chars[randomIndex] = randomNumeric.charAt(0);
        String newStr = String.valueOf(chars);

        return first + newStr;
    }
}
