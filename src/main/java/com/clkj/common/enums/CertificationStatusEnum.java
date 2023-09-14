package com.clkj.common.enums;

/**
 * 实名认证状态
 *
 * @author Created by yangliu on 2020/1/19
 */
public enum CertificationStatusEnum {
    /**
     * 未提交
     */
    uncommitted(-1),
    /**
     * 审核中
     */
    review(0),
    /**
     * 审核成功
     */
    success(1),
    /**
     * 审核失败
     */
    fail(2);

    /**
     * 值
     */
    private int value;

    /**
     * 构造器
     *
     * @param value 值
     */
    CertificationStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
