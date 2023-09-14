package com.clkj.common.utils;

import com.clkj.common.exception.RRException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * 密码验证redis工具类
 *
 * @author Created by YangLiu on 2020-08-03
 */
@AllArgsConstructor
@Component
public class PasswordRedisUtil {


    private final RedisUtils redisUtils;
    /**
     * 锁定账号时间
     */
    private static final int LOCK_HOURS = 1;
    /**
     * ?小时内，允许错误${MAX_ERROR_TIMES}次
     */
    private static final int ERROR_HOURS = 1;
    /**
     * 密码最大错误次数
     */
    private static final int MAX_ERROR_TIMES = 3;

    /**
     * 删除锁定
     *
     * @param userId userId
     */
    public void deleteLock(Long userId) {
        String lockKey = RedisKeys.getPasswordErrorLock(userId);
        redisUtils.delete(lockKey);
    }

    /**
     * 效验密码
     *
     * @param userId      userId
     * @param rawPassword rawPassword
     * @param salt        salt
     * @param password    password
     */
    public void check(Long userId, String rawPassword, String salt, String password) {

        String lockKey = RedisKeys.getPasswordErrorLock(userId);
        if (StringUtils.isNotBlank(lockKey)) {
            Long sec = redisUtils.getExpiry(lockKey, TimeUnit.SECONDS);
            throw new RRException("你的账号被锁定,请" + getWaitTimeStr(sec) + "后再登陆.");
        }

        // 密码正确
        if (PasswordUtil.check(rawPassword, salt, password)) {
            return;
        }

        String errorTimesKey = RedisKeys.getPasswordErrorTimes(userId);
        String errorTimesStr = redisUtils.get(errorTimesKey);

        int errorTimes = StringUtils.isBlank(errorTimesStr) ? 0 : Integer.parseInt(errorTimesStr);

        errorTimes++;

        if (errorTimes >= MAX_ERROR_TIMES) {
            redisUtils.lock(lockKey, "1", LOCK_HOURS, TimeUnit.HOURS);
            throw new RRException(MessageFormat.format("你的账号被锁定. 请{0}小时后再登陆", LOCK_HOURS));
        } else {
            if (errorTimes == 1) {
                // 第一次放入redis
                redisUtils.lock(errorTimesKey, "1", ERROR_HOURS, TimeUnit.HOURS);
            } else {
                // 更新次数，不更新失效时间
                redisUtils.update(errorTimesKey, String.valueOf(errorTimes), 0);
            }
            throw new RRException(getLoginFailTimesStr(errorTimes));
        }
    }

    private static String getWaitTimeStr(Long waitTime) {

        StringBuilder str = new StringBuilder();
        Long hour = waitTime / (60 * 60);
        if (hour > 0) {
            str.append(hour).append("小时");
        }
        Long minute = waitTime % (60 * 60) / 60;
        if (minute > 0) {
            str.append(minute).append("分钟");
        }
        Long second = waitTime % 60;
        if (second > 0) {
            str.append(second).append("秒");
        }
        return str.toString();
    }


    private static String getLoginFailTimesStr(Integer times) {
        if (times == null || times < 1) {
            return "";
        }
        StringBuilder str = new StringBuilder("账号或密码错误");
        if (times == 1) {
            str.append(",\n");
        } else {
            str.append(MessageFormat.format(" ({0}次),", times));
        }
        str.append(MessageFormat.format("如果错误{0}次,你的账号将被锁定", MAX_ERROR_TIMES));
        return str.toString();
    }
}
