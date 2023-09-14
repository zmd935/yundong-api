package com.clkj.common.enums;

import com.clkj.common.exception.RRException;
import lombok.Getter;

@Getter
public enum StatusEnum {
    //正常
    AVAILABLE(0),
    //删除
    UNAVAILABLE(1),
    ;

    private final Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public static StatusEnum resolve(Integer value) {
        for (StatusEnum anEnum : values()) {
            if (anEnum.getValue().equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public static StatusEnum valueOf(Integer value) {
        StatusEnum anEnum = resolve(value);
        if (anEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return anEnum;
    }


}
