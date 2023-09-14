package com.clkj.app.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.modules.sports.entity.SportsHomeEntity;

import java.util.List;

public interface AppSportsHomeService extends IService<SportsHomeEntity> {
    List getByName(String name);

    List getList();

    List getCourse(Long id);

    String getVideo(Long id);

    SportsHomeEntity getById(Long id);

}
