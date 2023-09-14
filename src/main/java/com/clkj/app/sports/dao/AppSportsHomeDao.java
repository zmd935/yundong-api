package com.clkj.app.sports.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppSportsHomeDao extends BaseMapper<SportsHomeEntity> {

    /**
     * 通过名字查询
     */
    List getByName(String name);

    /**
     * 获取运动推荐
     */
    List getList();

    /**
     * 各个种类的课程
     */
    List getCourse(Long id);

    /**
     * 点击打开视频
     */
    String getVideo(Long id);
}
