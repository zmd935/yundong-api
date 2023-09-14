package com.clkj.modules.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.modules.sports.dao.SportsCourseDao;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsCourseService;
import com.clkj.modules.sports.service.SportsTypeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SportsCourseServiceImpl extends ServiceImpl<SportsCourseDao, SportsHomeEntity> implements SportsCourseService {

    @Autowired
    SportsTypeService typeService;

    @Override
    public List getList(Map<String, Object> params) {

        List all = new ArrayList();

        List list = baseMapper.getSize();
        Integer size = list.size();
        for (int i=0;i<size;i++) {
            String id = (String) list.get(i);
            Long courseId = baseMapper.getCourseId(id);
            String courseType = typeService.getTypeById(courseId);
            Map course = baseMapper.getCourse(id);
            course.put("type", courseType);
            all.add(course);
        }
        return all;
    }

    @Override
    public void addList(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        String image = MapUtils.getString(params, "coverImage");
        Integer type = MapUtils.getInteger(params, "courseId");
        String detail = MapUtils.getString(params, "detail");
        String video = MapUtils.getString(params, "video");
        SportsHomeEntity homeEntity = new SportsHomeEntity();
        homeEntity.setName(name);
        homeEntity.setCoverImage(image);
        homeEntity.setCourseId(type);
        homeEntity.setDetail(detail);
        homeEntity.setVideo(video);
        homeEntity.setStatus(2);
        baseMapper.insert(homeEntity);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public SportsHomeEntity getInfo(Long id) {
        SportsHomeEntity homeEntity = baseMapper.selectById(id);
        Integer courseId = homeEntity.getCourseId();
        String courseType = typeService.getCourseType(courseId);
        homeEntity.setCourseType(courseType);
        return homeEntity;
    }
}
