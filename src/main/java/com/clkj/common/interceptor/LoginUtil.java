package com.clkj.common.interceptor;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.clkj.common.exception.RRSignatureException;
import com.clkj.common.exception.RRUnauthorizedException;
import com.clkj.common.utils.RedisUtils;
import com.clkj.common.utils.SignMd5Util;
import com.clkj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by YangLiu on 2020-07-31
 */
@Component
public class LoginUtil {

    private static final String USER_REDIS_PRE = "user_token_";
    private static final String USER_HEADER = "user-token";
    private static final String SIGNATURE = "sign";
    private static final String TIMESTAMP = "ts";

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 从request中获取userId
     * 已登录返回userId，未登录返回null。不做登录验证
     *
     * @param request request
     * @return session
     */
    public Long getUserId(HttpServletRequest request) {
        //获取用户凭证
        String token = request.getHeader(USER_HEADER);
        //凭证为空
        if (StringUtils.isBlank(token)) {
            return null;
        }

        return Optional.ofNullable(redisUtils.get(USER_REDIS_PRE + token, UserSession.class))
                .map(UserSession::getUserId)
                .orElse(null);
    }

    /**
     * 登录验证
     *
     * @param request request
     * @return session
     */
    public UserSession loginCheck(HttpServletRequest request) {
        //获取用户凭证
        String token = Optional.ofNullable(request.getHeader(USER_HEADER))
                .orElseThrow(() -> new RRUnauthorizedException("请传递token"));

        return Optional.ofNullable(redisUtils.get(USER_REDIS_PRE + token, UserSession.class))
                .orElseThrow(() -> new RRUnauthorizedException("没有登录或者登录已过期"));
    }

    /**
     * 签名验证
     *
     * @param request request
     * @param session session
     * @throws IOException io
     */
    public void signCheck(HttpServletRequest request, UserSession session) throws IOException {
        // body参数
        String body = IoUtil.read(request.getInputStream(), StandardCharsets.UTF_8);
        IoUtil.close(request.getInputStream());
        JSONObject obj = Optional.ofNullable(JSONObject.parseObject(body))
                .orElse(new JSONObject());

        // url参数
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            obj.put(name, request.getParameter(name));
        }

        // 没有传参数，不需要签名
        if (obj.isEmpty()) {
            return;
        }

        // 头部没有就从参数拿
        String rawSign = Optional.ofNullable(request.getHeader(SIGNATURE))
                .orElseGet(() -> request.getParameter(SIGNATURE));
        String timestamp = Optional.ofNullable(request.getHeader(TIMESTAMP))
                .orElseGet(() -> request.getParameter(TIMESTAMP));

        if (!SignMd5Util.checkSign(rawSign, obj, timestamp, session.getSecurityToken())) {
            throw new RRSignatureException("Invalid signature");
        }
    }

    /**
     * 登录
     *
     * @param token 登录token
     */
    public void login(String token, UserSession session) {
        redisUtils.expire((USER_REDIS_PRE + token), session, 31536000, TimeUnit.SECONDS);
    }

    /**
     * 退出登录
     *
     * @param token 登录token
     */
    public void logout(String token) {
        redisUtils.delete(USER_REDIS_PRE + token);
    }
}
