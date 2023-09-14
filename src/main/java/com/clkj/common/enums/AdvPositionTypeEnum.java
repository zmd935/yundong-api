package com.clkj.common.enums;

public enum AdvPositionTypeEnum {
    //广告位置
    HOMEPAGE(1, "首页"),
    STARTPAGE(2, "启动页"),
    ;


    private int key;
    private String remark;

    AdvPositionTypeEnum(int key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
