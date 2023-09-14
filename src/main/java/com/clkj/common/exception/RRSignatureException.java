package com.clkj.common.exception;

/**
 * 签名认证失败
 *
 * @author Created by YangLiu on 2020-07-28
 */
public class RRSignatureException extends RuntimeException {
    public RRSignatureException(String msg) {
        super(msg);
    }
}
