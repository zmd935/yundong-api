package com.clkj.common.annotation;

import java.lang.annotation.*;

/**
 * 加锁效验
 *
 * @author Created by YangLiu on 2020-08-05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    /**
     * 错误提示语句
     *
     * @return
     */
    String msg() default "请求频繁";

    /**
     * 加锁key
     *
     * @return
     */
    String key();
}
