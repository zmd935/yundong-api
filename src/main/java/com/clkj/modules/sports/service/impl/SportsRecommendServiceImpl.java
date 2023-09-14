package com.clkj.modules.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.modules.sports.dao.SportsRecommendDao;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsRecommendService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SportsRecommendServiceImpl extends ServiceImpl<SportsRecommendDao, SportsHomeEntity> implements SportsRecommendService {

    @Override
    public List getList(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        if (name.length()>0) {
            List list = baseMapper.getByName(name);
            return list;
        } else {
            List list = baseMapper.getList();
            return list;
        }
    }

    @Override
    public void addList(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        String coverImage = MapUtils.getString(params, "coverImage");
        String courseType = MapUtils.getString(params, "courseType");
        String sportsTime = MapUtils.getString(params, "sportsTime");
        String video = MapUtils.getString(params, "video");
        SportsHomeEntity homeEntity = new SportsHomeEntity();
        homeEntity.setName(name);
        homeEntity.setCourseType(courseType);
        homeEntity.setCoverImage(coverImage);
        homeEntity.setSportsTime(sportsTime);
        homeEntity.setVideo(video);
        homeEntity.setStatus(1);
        baseMapper.insert(homeEntity);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

}
