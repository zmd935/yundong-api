package com.clkj.modules.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.modules.sports.entity.SportsHomeEntity;

import java.util.List;
import java.util.Map;

public interface SportsBannerService extends IService<SportsHomeEntity> {

    List getList();

    void deleteById(Long id);

    void addList(Map<String, Object> params);
}
