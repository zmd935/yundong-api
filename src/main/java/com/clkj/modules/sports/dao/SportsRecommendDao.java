package com.clkj.modules.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SportsRecommendDao extends BaseMapper<SportsHomeEntity> {

    List getList();

    List getByName(String name);
}
