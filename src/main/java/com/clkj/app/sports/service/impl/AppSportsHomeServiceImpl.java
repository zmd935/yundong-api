package com.clkj.app.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.app.sports.dao.AppSportsHomeDao;
import com.clkj.app.sports.service.AppSportsHomeService;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppSportsHomeServiceImpl extends ServiceImpl<AppSportsHomeDao, SportsHomeEntity> implements AppSportsHomeService {

    @Override
    public List getByName(String name) {
        return baseMapper.getByName(name);
    }

    @Override
    public List getList() {
        return baseMapper.getList();
    }

    @Override
    public List getCourse(Long id) {
        return baseMapper.getCourse(id);
    }

    @Override
    public String getVideo(Long id) {
        return baseMapper.getVideo(id);
    }

    @Override
    public SportsHomeEntity getById(Long id) {
        return baseMapper.selectById(id);
    }
}
