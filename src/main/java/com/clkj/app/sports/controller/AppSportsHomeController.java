package com.clkj.app.sports.controller;

import com.clkj.app.sports.service.AppSportsHomeService;
import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 首页
 */

@AllArgsConstructor
@RestController
@RequestMapping("/app/sports/home")
public class AppSportsHomeController {

    private AppSportsHomeService homeService;

    @SysLog("搜索查询")
    @GetMapping
    public R<?> getByName(String name) {
        List list = homeService.getByName(name);
        return R.data(list);
    }

    @SysLog("获取数据")
    @GetMapping("/getList")
    public R<?> getList() {
        List list = homeService.getList();
        return R.data(list);
    }

    @SysLog("点击打开视频")
    @GetMapping("/getVideo")
    public R<?> getVideo(Long id) {
        String video = homeService.getVideo(id);
        return R.data(video);
    }

    @SysLog("视频下方的简介")
    @GetMapping("/getById")
    public R<?> getById(Long id) {
        SportsHomeEntity homeEntity = homeService.getById(id);
        return R.data(homeEntity);
    }
}
