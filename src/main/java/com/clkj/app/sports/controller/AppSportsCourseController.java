package com.clkj.app.sports.controller;

import com.clkj.app.sports.service.AppSportsCourseService;
import com.clkj.app.sports.service.AppSportsHomeService;
import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 课程种类
 */

@AllArgsConstructor
@RestController
@RequestMapping("/app/sports/course")
public class AppSportsCourseController {

    private AppSportsCourseService courseService;
    private AppSportsHomeService homeService;

    @SysLog("获取课程种类")
    @GetMapping
    public R<?> getName() {
        List name = courseService.getName();
        return R.data(name);
    }

    @SysLog("各个种类的课程")
    @GetMapping("/getById")
    public R<?> getCourse(Long id) {
        List list = homeService.getCourse(id);
        return R.data(list);
    }
}
