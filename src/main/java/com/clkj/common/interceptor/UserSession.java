package com.clkj.common.interceptor;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录后，存储在redis的对象
 *
 * @author Created by YangLiu on 2020-07-27
 */
@Setter
@Getter
public class UserSession {
    /**
     * 用户id
     */
    Long userId;
    /**
     * 安全token，用于签名认证
     */
    String securityToken;
}
