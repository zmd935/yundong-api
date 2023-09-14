/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.common.exception;

import com.clkj.common.i18n.Message;
import com.clkj.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class RRExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R<?> handleRRException(RRException e, HttpServletRequest req) {
        if (e.isPrint()) {
            log.error(req.getRequestURI(), e);
        }
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 权限不足
     */
    @ExceptionHandler(RRPermsException.class)
    public R<?> handleRRPermsException(RRPermsException e) {
        return R.error(401, e.getMessage());
    }

    /**
     * 未登录，或者登录失败
     */
    @ExceptionHandler(RRUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<?> handleRRUnauthorizedException(RRUnauthorizedException e) {
        return R.error(401, e.getMessage());
    }

    /**
     * 签名认证失败
     */
    @ExceptionHandler(RRSignatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<?> handleRRSignatureException(RRSignatureException e) {
        return R.error(403, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(404, Message.getMessage("common.exception.404"));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return R.error(Message.getMessage("common.exception.existed"));
    }

    @ExceptionHandler(AuthorizationException.class)
    public R<?> handleauthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return R.error(Message.getMessage("common.exception.authorization"));
    }

    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e, HttpServletRequest req) {
        //在报exception，添加uri的查看
        log.error(req.getRequestURI(), e);
        return R.error(Message.getMessage("common.exception.500"));
    }
}
