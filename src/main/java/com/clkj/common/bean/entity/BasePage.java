package com.clkj.common.bean.entity;

import com.github.pagehelper.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * app分页对象
 * 搭配pageHelper使用
 * page默认为1，pageSize默认为10
 *
 * @author tuooos
 * @create 2020-08-04 17:59
 */
@ApiModel
public class BasePage implements IPage {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer pageNum = 1;
    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;

    @Override
    public Integer getPageNum() {
        return this.pageNum < 1 ? 1 : this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Integer getPageSize() {
        return this.pageSize < 1 ? 1 : this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

}
