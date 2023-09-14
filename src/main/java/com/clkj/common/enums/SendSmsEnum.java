package com.clkj.common.enums;

public enum SendSmsEnum {
    //短信类型
    REGISTERED(1, "注册", "SMS_187241234"),
    RESET_PASSWORD(2, "重置密码", "SMS_187241236"),
    LOGIN(3, "验证码登录", "SMS_187271266"),
    CHANGE_PHONE(4, "手机换绑", "SMS_187261207"),
    VERIFIED(5, "实名认证", "SMS_187271269");


    private int key;
    private String remark;
    private String name;

    SendSmsEnum(int key, String remark, String name) {
        this.key = key;
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
