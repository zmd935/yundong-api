package com.clkj.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.clkj.common.bean.entity.CacheCode;
import com.clkj.common.enums.msg.SendCodeTypeEnum;
import com.clkj.common.exception.RRException;
import com.clkj.common.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 发送验证码redis工具类
 *
 * @author Created by YangLiu on 2020-08-03
 */
@Component
public class SendCodeRedisUtil {
    @Autowired
    RedisUtils redisUtils;

    /**
     * 把code存入redis
     *
     * @param typeEnum   code类型
     * @param username   手机号或邮箱
     * @param code       验证码
     * @param expiryTime 过期时间（秒）
     */
    public void setCode(SendCodeTypeEnum typeEnum, String username, String code, long expiryTime) {
        CacheCode cacheCode = new CacheCode();
        cacheCode.setType(typeEnum.name());
        cacheCode.setCode(code);
        cacheCode.setLimit(System.currentTimeMillis() + expiryTime);
        redisUtils.expire(getKey(username), JSONObject.toJSONString(cacheCode), expiryTime, TimeUnit.SECONDS);
    }

    /**
     * 从redis去除验证码
     *
     * @param username 手机号或邮箱
     * @return CacheCode
     */
    public CacheCode getCode(String username) {
        String str = redisUtils.get(getKey(username));

        return str == null ? null : JSONObject.parseObject(str, CacheCode.class);
    }

    /**
     * 删除redis验证码
     *
     * @param username 手机号或邮箱
     * @param value    value
     * @param offset   offset
     */
    public void updateCode(String username, String value, long offset) {
        redisUtils.update(getKey(username), value, offset);
    }

    /**
     * 删除redis验证码
     *
     * @param username 手机号或邮箱
     */
    public void deleteCode(String username) {
        redisUtils.delete(getKey(username));
    }

    /**
     * 获得redis key
     *
     * @param username 手机号或邮箱
     * @return String
     */
    private String getKey(String username) {
        return RedisKeys.getSendCodeKey(username);
    }

    /**
     * 效验验证码
     *
     * @param code       code
     * @param username   username
     * @param type       type
     * @param deleteFlag 验证成功后是否删除
     */
    public void checkCode(String code, String username, SendCodeTypeEnum type, boolean deleteFlag) {
        CacheCode cacheCode = this.getCode(username);
        Assert.notNull(cacheCode, "Please send code first，or the code has expired");
        // 验证类型不对
        if (!cacheCode.getType().equals(type.name())) {
            this.deleteCode(username);
            throw new RRException("Please send code first，or the code has expired");
        }
        // 验证码错误
        if (!cacheCode.getCode().equals(code)) {
            // 增加错误次数
            cacheCode.setErrorTimes(cacheCode.getErrorTimes() + 1);
            // 最大错误次数
            int maxErrorTimes = 3;
            if (cacheCode.getErrorTimes() >= maxErrorTimes) {
                // 验证码错误3次就需要重新发送
                this.deleteCode(username);
                throw new RRException("Invalid verify code，Please resend code");
            } else {
                // 重新缓存对象，不修改时间
                this.updateCode(username, JSONObject.toJSONString(cacheCode), 0);
            }
            throw new RRException("Invalid verify code");
        }

        if (deleteFlag) {
            this.deleteCode(username);
        }
    }
}
