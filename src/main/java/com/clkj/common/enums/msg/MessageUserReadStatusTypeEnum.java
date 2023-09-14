package com.clkj.common.enums.msg;

import lombok.Getter;

@Getter
public enum MessageUserReadStatusTypeEnum {
    //未读
    UNREAD(0),
    //已读
    READ(1);

    private Integer value;

    MessageUserReadStatusTypeEnum(Integer value) {
        this.value = value;
    }

}
