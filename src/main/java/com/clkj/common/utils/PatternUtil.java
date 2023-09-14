package com.clkj.common.utils;

import cn.hutool.core.lang.Validator;

import java.util.regex.Pattern;

/**
 * @author Created by YangLiu on 2021-05-21
 */
public class PatternUtil {
    /**
     * 手机账号11位数字效验
     */
    public static Pattern PHONE_PATTERN = Pattern.compile("^\\d{11}$");
    /**
     * 座机格式校验：023-12345678
     */
    public static Pattern LANDLINE_PATTERN = Pattern.compile("^\\d{3}-\\d{8}|\\d{4}-\\d{7}$");
    /**
     * 字母数字账号效验
     * 用户名长度8~20位,首位必须是字母，数字可有可无
     */
    public static Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z\\d]{7,19}$");
    /**
     * 字母数字密码效验
     * 密码长度8~20位,必须包含一个大写，一个小写，一个数字
     */
    public static Pattern PWD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\!\\@\\#\\$\\%\\^\\&\\;\\*\\(\\)\\-\\_\\=\\+]{8,20}$");

    /**
     * 效验
     *
     * @param p pattern
     * @param w 字符
     * @return boolean
     */
    private static boolean matches(Pattern p, String w) {
        return p.matcher(w).matches();
    }

    /**
     * 是否是有效的邮箱
     *
     * @param w 字符
     * @return boolean
     */
    public static boolean isEmail(String w) {
        return Validator.isEmail(w);
    }


    /**
     * 是否是有效的手机号
     *
     * @param w 字符
     * @return boolean
     */
    public static boolean isPhone(String w) {
        return matches(PHONE_PATTERN, w);
    }

    /**
     * 是否是有效的座机号
     *
     * @param w 字符
     * @return boolean
     */
    public static boolean isLandline(String w) {
        return matches(LANDLINE_PATTERN, w);
    }

    /**
     * 是否是有效的密码
     *
     * @param w 字符
     * @return boolean
     */
    public static boolean isValidUsername(String w) {
        return matches(USERNAME_PATTERN, w);
    }

    /**
     * 是否是有效的密码
     *
     * @param w 字符
     * @return boolean
     */
    public static boolean isValidPassword(String w) {
        return matches(PWD_PATTERN, w);
    }

}
