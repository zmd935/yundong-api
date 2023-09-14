package com.clkj.modules.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SportsCourseDao extends BaseMapper<SportsHomeEntity> {

    List getSize();

    Long getCourseId(String id);

    Map<String, Object> getCourse(String id);
}
