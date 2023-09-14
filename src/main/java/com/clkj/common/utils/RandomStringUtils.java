package com.clkj.common.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * 生成数字大小写字母，指定长度字符串
 *
 * @author Created by YangLiu on 2023-01-10
 */
public class RandomStringUtils {
    /**
     * 去掉ilo的大小写
     */
    private static final String BASE_STRING = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ0123456789";

    public static String getCode(int len) {
        return RandomUtil.randomString(BASE_STRING, len);
    }

}
