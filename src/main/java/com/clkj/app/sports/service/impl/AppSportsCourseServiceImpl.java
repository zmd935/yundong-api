package com.clkj.app.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.app.sports.dao.AppSportsCourseDao;
import com.clkj.app.sports.service.AppSportsCourseService;
import com.clkj.modules.sports.entity.SportsCourseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppSportsCourseServiceImpl extends ServiceImpl<AppSportsCourseDao, SportsCourseEntity> implements AppSportsCourseService {


    @Override
    public List getName() {
        List name = baseMapper.getName();
        return name;
    }

}
