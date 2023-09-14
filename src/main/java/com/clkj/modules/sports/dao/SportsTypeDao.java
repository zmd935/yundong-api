package com.clkj.modules.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsCourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SportsTypeDao extends BaseMapper<SportsCourseEntity> {

    String getTypeById(Long courseId);

    List getId();

    String getName(Long id);

    List getList();

    String getCourseType(Integer courseId);
}
