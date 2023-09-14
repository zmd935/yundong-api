package com.clkj.modules.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.modules.sports.entity.SportsCourseEntity;

import java.util.List;
import java.util.Map;

public interface SportsTypeService extends IService<SportsCourseEntity> {

    String getTypeById(Long courseId);

    List typeList();

    List getList();

    void addList(Map<String, Object> params);

    String getCourseType(Integer courseId);

}
