package com.clkj.modules.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.modules.sports.entity.SportsHomeEntity;

import java.util.List;
import java.util.Map;

public interface SportsRecommendService extends IService<SportsHomeEntity> {

    List getList(Map<String, Object> params);

    void addList(Map<String, Object> params);

    void deleteById(Long id);

}
