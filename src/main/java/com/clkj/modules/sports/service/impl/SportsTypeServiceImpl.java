package com.clkj.modules.sports.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clkj.modules.sports.dao.SportsTypeDao;
import com.clkj.modules.sports.entity.SportsCourseEntity;
import com.clkj.modules.sports.service.SportsTypeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SportsTypeServiceImpl extends ServiceImpl<SportsTypeDao, SportsCourseEntity> implements SportsTypeService {

    @Override
    public String getTypeById(Long courseId) {
        return baseMapper.getTypeById(courseId);
    }

    @Override
    public List typeList() {
        List ids = baseMapper.getId();
        Integer size = ids.size();
        List all = new ArrayList();
        for (int i=0;i<size;i++) {
            Long id = (Long) ids.get(i);
            String name = baseMapper.getName(id);
            Map<String, Object> map = new HashMap<>();
            map.put("value",i+1);
            map.put("label",name);
            all.add(map);
        }
        return all;
    }

    @Override
    public List getList() {
        return baseMapper.getList();
    }

    @Override
    public void addList(Map<String, Object> params) {
        String name = MapUtils.getString(params, "name");
        SportsCourseEntity courseEntity = new SportsCourseEntity();
        courseEntity.setName(name);
        baseMapper.insert(courseEntity);
    }

    @Override
    public String getCourseType(Integer courseId) {
        return baseMapper.getCourseType(courseId);
    }


}
