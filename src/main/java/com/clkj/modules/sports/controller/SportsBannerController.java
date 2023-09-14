package com.clkj.modules.sports.controller;

import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsBannerService;
import com.clkj.modules.sys.controller.AbstractController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/sports/banner")
public class SportsBannerController extends AbstractController {

    private SportsBannerService bannerService;

    @SysLog("获取走马灯列表")
    @GetMapping
    public R<?> getList() {
        List list = bannerService.getList();
        return R.data(list);
    }

    @SysLog("删除走马灯")
    @PostMapping
    public R<?> deleteById(@RequestBody Long id) {
        bannerService.deleteById(id);
        return R.ok();
    }

    @SysLog("保存")
    @PostMapping("/save")
    public R<?> addList(@RequestBody Map<String, Object> params) {
        bannerService.addList(params);
        return R.ok();
    }

    @SysLog("修改")
    @GetMapping("/bannerId/{id}")
    public R<?> info(@PathVariable("id") Long id) {
        SportsHomeEntity homeEntity = bannerService.getById(id);
        return R.data(homeEntity);
    }
}