package com.clkj.common.exception;

/**
 * 未登录，或者登录失效
 *
 * @author Created by YangLiu on 2020-07-28
 */
public class RRUnauthorizedException extends RuntimeException {
    public RRUnauthorizedException(String msg) {
        super(msg);
    }
}
