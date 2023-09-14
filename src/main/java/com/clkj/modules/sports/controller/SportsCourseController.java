package com.clkj.modules.sports.controller;

import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import com.clkj.modules.sports.entity.SportsHomeEntity;
import com.clkj.modules.sports.service.SportsCourseService;
import com.clkj.modules.sports.service.SportsTypeService;
import com.clkj.modules.sys.controller.AbstractController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/sports/course")
public class SportsCourseController extends AbstractController {

    private SportsCourseService courseService;
    private SportsTypeService typeService;

    @SysLog("获取课程")
    @GetMapping
    public R<?> list(@RequestParam Map<String, Object> params) {
        List list = courseService.getList(params);
        return R.data(list);
    }

    @SysLog("获取课程种类")
    @GetMapping("/typeList")
    public R<?> typeList() {
        List list = typeService.typeList();
        return R.data(list);
    }

    @SysLog("新增运动")
    @PostMapping
    public R<?> addList(@RequestBody Map<String, Object> params) {
        courseService.addList(params);
        return R.ok();
    }

    @SysLog("删除课程")
    @PostMapping("/delete")
    public R<?> delete(@RequestBody Long id) {
        courseService.deleteById(id);
        return R.ok();
    }

    @SysLog("修改")
    @GetMapping("/info/{id}")
    public R<?> info(@PathVariable("id") Long id) {
        SportsHomeEntity homeEntity = courseService.getInfo(id);
        return R.data(homeEntity);
    }
}
