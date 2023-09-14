package com.clkj.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RedisKeys {
    /**
     * 系统配置
     *
     * @param key key
     * @return String
     */
    public static String getSysConfigKey(String key) {
        return RedisKeyEnum.SYS_CONFIG.getValue() + key;
    }

    /**
     * 验证码图片
     *
     * @param uuid uuid
     * @return String
     */
    public static String getSysCaptchaKey(String uuid) {
        return RedisKeyEnum.SYS_CAPTCHA.getValue() + uuid;
    }

    /**
     * 发送短信每分钟手机限制
     *
     * @param phone 手机号
     * @return String
     */
    public static String getSendCodeLimitKey(String phone) {
        return RedisKeyEnum.SEND_CODE_LIMIT_.getValue() + phone;
    }

    /**
     * 发送短信保存code
     *
     * @param phone 手机号
     * @return String
     */
    public static String getSendCodeKey(String phone) {
        return RedisKeyEnum.SEND_CODE_.getValue() + "_" + phone;
    }

    /**
     * 新增订单加锁
     *
     * @param userId 用户id
     * @return String
     */
    public static String getLockOrderSave(Long userId) {
        return RedisKeyEnum.LOCK_ORDER_SAVE_.getValue() + userId;
    }

    /**
     * 密码错误锁定
     *
     * @param userId userId
     * @return String
     */
    public static String getPasswordErrorLock(Long userId) {
        return RedisKeyEnum.PASSWORD_ERROR_LOCK_.getValue() + userId;
    }

    /**
     * 密码错误次数
     *
     * @param userId userId
     * @return String
     */
    public static String getPasswordErrorTimes(Long userId) {
        return RedisKeyEnum.PASSWORD_ERROR_TIMES_.getValue() + userId;
    }

    public enum RedisKeyEnum {
        /**
         * 系统配置
         */
        SYS_CONFIG("SYS_CONFIG"),
        /**
         * 验证码图片
         */
        SYS_CAPTCHA("SYS_CAPTCHA_"),
        /**
         * 发送验证码限制
         */
        SEND_CODE_LIMIT_("SEND_CODE_LIMIT_"),
        /**
         * 发送验证码
         */
        SEND_CODE_("SEND_CODE_"),
        /**
         * 新增订单锁
         */
        LOCK_ORDER_SAVE_("LOCK_ORDER_SAVE_"),
        /**
         * 密码错误锁定
         */
        PASSWORD_ERROR_LOCK_("PASSWORD_ERROR_LOCK_"),
        /**
         * 密码错误次数
         */
        PASSWORD_ERROR_TIMES_("PASSWORD_ERROR_TIMES_"),
        ;

        RedisKeyEnum(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }
}
