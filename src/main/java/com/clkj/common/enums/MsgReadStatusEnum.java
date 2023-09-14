package com.clkj.common.enums;

/**
 * 消息已/未读
 */
public enum MsgReadStatusEnum {
    //未读
    UNREAD(0),
    //已读
    HAVE_READ(1);

    private final Integer readStatus;

    MsgReadStatusEnum(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getReadStatus() {
        return readStatus;
    }
}
