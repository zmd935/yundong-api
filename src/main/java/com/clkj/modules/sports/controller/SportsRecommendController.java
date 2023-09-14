package com.clkj.modules.sports.controller;

import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsRecommendService;
import com.clkj.modules.sys.controller.AbstractController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/sports/recommend")
public class SportsRecommendController extends AbstractController {

    private SportsRecommendService recommendService;

    @SysLog("获取和查询")
    @GetMapping
    public R<?> getList(@RequestParam Map<String, Object> params) {
        List list = recommendService.getList(params);
        return R.data(list);
    }

    @SysLog("新增运动")
    @PostMapping
    public R<?> addList(@RequestBody Map<String, Object> params) {
        recommendService.addList(params);
        return R.ok();
    }

    @SysLog("删除运动")
    @PostMapping("/delete")
    public R<?> delete(@RequestBody Long id) {
        recommendService.deleteById(id);
        return R.ok();
    }

    @SysLog("修改")
    @GetMapping("/info/{id}")
    public R<?> info(@PathVariable("id") Long id) {
        SportsHomeEntity homeEntity = recommendService.getById(id);
        return R.data(homeEntity);
    }
}
