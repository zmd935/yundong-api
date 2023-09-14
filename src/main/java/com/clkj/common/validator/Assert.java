package com.clkj.common.validator;

import com.clkj.common.exception.RRException;
import com.clkj.common.utils.StringUtils;

import java.math.BigDecimal;

/**
 * 数据校验
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new RRException(message);
        }
    }


    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new RRException(message);
        }
    }

    public static void equalsTo(Object obj1, Object obj2, String message) {
        if (!obj1.equals(obj2)) {
            throw new RRException(message);
        }
    }

    public static void notEqualsTo(Object obj1, Object obj2, String message) {
        if (obj1.equals(obj2)) {
            throw new RRException(message);
        }
    }

    public static void greaterThan(Number num1, Number num2, String message) {

        if (new BigDecimal(num1.toString()).compareTo(new BigDecimal(num2.toString())) <= 0) {
            throw new RRException(message);
        }
    }

    public static void greaterThanOrEquals(Number obj1, Number obj2, String message) {

        if (new BigDecimal(obj1.toString()).compareTo(new BigDecimal(obj2.toString())) < 0) {
            throw new RRException(message);
        }
    }

    public static void lessThanOrEquals(Number obj1, Number obj2, String message) {

        if (new BigDecimal(obj1.toString()).compareTo(new BigDecimal(obj2.toString())) > 0) {
            throw new RRException(message);
        }
    }

    /**
     * 断言为true
     *
     * @param bool    bool值
     * @param message 不为true的错误消息
     */
    public static void beTrue(boolean bool, String message) {
        if (!bool) {
            throw new RRException(message);
        }
    }

    /**
     * 断言不为true
     *
     * @param bool    bool值
     * @param message 为true的错误消息
     */
    public static void notTrue(boolean bool, String message) {
        if (bool) {
            throw new RRException(message);
        }
    }
}
