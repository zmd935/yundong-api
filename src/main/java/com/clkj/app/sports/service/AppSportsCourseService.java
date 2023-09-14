package com.clkj.app.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.modules.sports.entity.SportsCourseEntity;

import java.util.List;

public interface AppSportsCourseService extends IService<SportsCourseEntity> {

    List getName();


}
