package com.clkj.modules.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.modules.sports.dao.SportsBannerDao;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsBannerService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SportsBannerServiceImpl extends ServiceImpl<SportsBannerDao, SportsHomeEntity> implements SportsBannerService {

    @Override
    public List getList() {
        return baseMapper.getList();
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void addList(Map<String, Object> params) {
        String image = MapUtils.getString(params, "image");
        String video = MapUtils.getString(params, "video");
        SportsHomeEntity homeEntity = new SportsHomeEntity();
        homeEntity.setCoverImage(image);
        homeEntity.setVideo(video);
        homeEntity.setStatus(0);
        baseMapper.insert(homeEntity);
    }
}
