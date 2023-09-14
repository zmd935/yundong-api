package com.clkj.common.enums;

public enum SmsCodeTypeEnum {
    //注册
    REGISTER("注册", 1),
    LOGIN("登录", 2),
    //修改密码
    UPDATE_PASSWORD("修改密码", 3);

    private final String title;
    private final Integer type;

    SmsCodeTypeEnum(String title, Integer type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getType() {
        return type;
    }
}
