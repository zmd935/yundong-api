package com.clkj.common.bean.entity;

import lombok.Data;

import java.util.List;

/**
 * element-ui：cascade级联通用封装类
 *
 * @author Created by YangLiu on 2020-08-31
 */
@Data
public class CascadeNode {

    private Long value;

    private String label;

    private List<CascadeNode> children;
}
