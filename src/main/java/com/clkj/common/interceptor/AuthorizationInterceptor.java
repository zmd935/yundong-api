package com.clkj.common.interceptor;


import com.clkj.common.annotation.Lock;
import com.clkj.common.annotation.Login;
import com.clkj.common.utils.RedisUtils;
import com.clkj.common.validator.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 权限(Token)验证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LoginUtil loginUtil;

    public static final String USER_ID_ATTRIBUTE_KEY = "userId";
    public static final String SESSION_ATTRIBUTE_KEY = "session";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Headers", "user-token");

        if (!(handler instanceof HandlerMethod)) {
            log.info("void uri：" + request.getRequestURI());
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断是否需要登录
        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (login == null) {
            return true;
        }

        //登录验证，并返回注解
        UserSession session = loginUtil.loginCheck(request);

        //签名认证
        loginUtil.signCheck(request, session);

        //判断是否有加锁的注解
        Optional.ofNullable(handlerMethod.getMethodAnnotation(Lock.class))
                .ifPresent(lockAnno -> {
                    boolean lock = redisUtils.lock(lockAnno.key() + session.getUserId(), "1", 5, TimeUnit.SECONDS);
                    Assert.beTrue(lock, lockAnno.msg());
                });

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_ID_ATTRIBUTE_KEY, session.getUserId());
        request.setAttribute(SESSION_ATTRIBUTE_KEY, session);
        return true;
    }

}
