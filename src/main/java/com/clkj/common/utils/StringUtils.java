package com.clkj.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * 字符串判断
 *
 * @author Created by YangLiu on 2020-10-12
 */
public class StringUtils {
    public static String replace(String text, String searchString, String replacement) {
        return StrUtil.replace(text, searchString, replacement);
    }

    public static String substringBeforeLast(String text, String separator) {
        return StrUtil.subBefore(text, separator, true);
    }

    public static String substringAfterLast(String text, String separator) {
        return StrUtil.subAfter(text, separator, true);
    }

    public static boolean isEmpty(String text) {
        return StrUtil.isEmpty(text);
    }

    public static boolean isNotEmpty(String text) {
        return StrUtil.isNotEmpty(text);
    }

    public static boolean isBlank(String text) {
        return StrUtil.isBlank(text);
    }

    public static boolean isNotBlank(String text) {
        return StrUtil.isNotBlank(text);
    }

    public static String join(String separator, List<?> list) {
        return StrUtil.join(separator, list);
    }

}
