package com.clkj.modules.sports.controller;

import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.R;
import com.clkj.modules.sports.service.SportsTypeService;
import com.clkj.modules.sys.controller.AbstractController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/sports/type")
public class SportsTypeController extends AbstractController {

    private SportsTypeService typeService;

    @SysLog("获取课程种类")
    @GetMapping
    public R<?> getList() {
        List list = typeService.getList();
        return R.data(list);
    }

    @SysLog("删除课程种类")
    @PostMapping("/delete")
    public R<?> deleteById(@RequestBody Long id) {
        typeService.removeById(id);
        return R.ok();
    }

    @SysLog("新增种类")
    @PostMapping
    public R<?> addList(@RequestBody Map<String, Object> params) {
        typeService.addList(params);
        return R.ok();
    }


}
