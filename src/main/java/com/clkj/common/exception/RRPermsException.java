package com.clkj.common.exception;

/**
 * 权限不足
 *
 * @author Created by YangLiu on 2020-08-18
 */
public class RRPermsException extends RuntimeException {
    public RRPermsException(String msg) {
        super(msg);
    }
}
