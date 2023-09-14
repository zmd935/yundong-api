package com.clkj.app.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsCourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppSportsCourseDao extends BaseMapper<SportsCourseEntity> {

    /**
     * 获取课程种类
     */
    List getName();
}
