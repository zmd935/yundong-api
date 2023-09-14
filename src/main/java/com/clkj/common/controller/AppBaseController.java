package com.clkj.common.controller;

import com.clkj.common.interceptor.AuthorizationInterceptor;
import com.clkj.common.interceptor.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by yangliu on 2020/1/17
 */
public class AppBaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected Long getUserId() {
        return (Long) request.getAttribute(AuthorizationInterceptor.USER_ID_ATTRIBUTE_KEY);
    }

    protected UserSession getSession() {
        return (UserSession) request.getAttribute(AuthorizationInterceptor.SESSION_ATTRIBUTE_KEY);
    }
}
