package com.clkj.common.enums.msg;

import com.clkj.common.exception.RRException;

/**
 * @author Created by YangLiu on 2020/7/9
 */
public enum SendCodeTypeEnum {
    /**
     * 注册，已注册就登录
     */
    register(1, "SMS_187241234"),
    /**
     * 登录，未注册就去注册
     */
    login(2, "SMS_187271266"),
    /**
     * 忘记密码
     */
    forget_password(3, "SMS_187271266"),
    /**
     * 修改手机号-原手机号
     */
    update_phone_old(4, "SMS_187271266"),
    /**
     * 修改手机号-新手机号
     */
    update_phone_new(5, "SMS_187271266"),
    /**
     * 设置支付密码
     */
    set_pin_code(6, "SMS_187271266"),
    ;

    SendCodeTypeEnum(Integer value, String templateCode) {
        this.value = value;
        this.templateCode = templateCode;
    }

    public static SendCodeTypeEnum resolve(Integer value) {
        for (SendCodeTypeEnum sendCodeTypeEnum : values()) {
            if (sendCodeTypeEnum.getValue().equals(value)) {
                return sendCodeTypeEnum;
            }

        }
        return null;
    }

    public static SendCodeTypeEnum valueOf(Integer value) {
        SendCodeTypeEnum sendCodeTypeEnum = resolve(value);
        if (sendCodeTypeEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return sendCodeTypeEnum;
    }


    private Integer value;
    private String templateCode;

    public Integer getValue() {
        return value;
    }

    public String getTemplateCode() {
        return templateCode;
    }
}
