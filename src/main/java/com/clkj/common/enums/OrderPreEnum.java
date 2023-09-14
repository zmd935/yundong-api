package com.clkj.common.enums;

import com.clkj.common.exception.RRException;
import lombok.Getter;

/**
 * 订单前缀
 *
 * @author Created by YangLiu on 2022-12-12
 */
@Getter
public enum OrderPreEnum {
    /**
     * 订单
     */
    ORDER_PRE(10),
    /**
     * 支付
     */
    PAY_PRE(18),
    ;

    private final Integer value;

    OrderPreEnum(Integer value) {
        this.value = value;
    }

    public static OrderPreEnum resolve(Integer value) {
        for (OrderPreEnum anEnum : values()) {
            if (anEnum.getValue().equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public static OrderPreEnum valueOf(Integer value) {
        OrderPreEnum anEnum = resolve(value);
        if (anEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return anEnum;
    }


}
