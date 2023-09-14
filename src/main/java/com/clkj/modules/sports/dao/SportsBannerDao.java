package com.clkj.modules.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SportsBannerDao extends BaseMapper<SportsHomeEntity> {

    /**
     * 获取所有走马灯
     */
    List getList();

    /**
     * 新增走马灯
     */
    void addList(String image);
}
