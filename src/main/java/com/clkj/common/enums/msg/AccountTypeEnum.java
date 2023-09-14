package com.clkj.common.enums.msg;

import com.clkj.common.exception.RRException;
import lombok.Getter;

/**
 * 账号类型
 *
 * @author Created by YangLiu on 2022/12/6
 */
@Getter
public enum AccountTypeEnum {
    /**
     * 手机
     */
    MOBILE(1),
    /**
     * 邮箱
     */
    EMAIL(2);

    private final Integer type;

    AccountTypeEnum(Integer type) {
        this.type = type;
    }

    public static AccountTypeEnum resolve(Integer value) {
        for (AccountTypeEnum typeEnum : values()) {
            if (typeEnum.getType().equals(value)) {
                return typeEnum;
            }

        }
        return null;
    }

    public static AccountTypeEnum valueOf(Integer value) {
        AccountTypeEnum typeEnum = resolve(value);
        if (typeEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return typeEnum;
    }

}
