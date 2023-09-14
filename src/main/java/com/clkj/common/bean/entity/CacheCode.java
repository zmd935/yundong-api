package com.clkj.common.bean.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by YangLiu on 2022-12-06
 */
@Data
public class CacheCode implements Serializable {
    /**
     * 类型
     */
    String type;
    /**
     * 验证码
     */
    String code;
    /**
     * 限制时间
     */
    Long limit;
    /**
     * 输入错误次数
     */
    Integer errorTimes = 0;
}
