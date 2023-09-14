package com.clkj.common.enums.msg;

import com.clkj.common.exception.RRException;
import lombok.Getter;

@Getter
public enum MsgTypeEnum {
    //系统消息
    SYSTEM("系统消息", 1),
    //个人消息
    PERSON("个人消息", 2),
    ;

    private final String value;
    private final Integer type;

    MsgTypeEnum(String value, Integer type) {
        this.value = value;
        this.type = type;
    }

    public static MsgTypeEnum resolve(Integer value) {
        for (MsgTypeEnum typeEnum : values()) {
            if (typeEnum.getType().equals(value)) {
                return typeEnum;
            }

        }
        return null;
    }

    public static MsgTypeEnum valueOf(Integer value) {
        MsgTypeEnum typeEnum = resolve(value);
        if (typeEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return typeEnum;
    }


}
