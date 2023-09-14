/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.modules.sys.controller;

import com.clkj.common.utils.PageUtils;
import com.clkj.common.utils.R;
import com.clkj.modules.sys.entity.SysLogEntity;
import com.clkj.modules.sys.service.SysLogService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 列表
     */

    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R<PageUtils<SysLogEntity>> list(@RequestParam Map<String, Object> params) {
        PageUtils<SysLogEntity> page = sysLogService.queryPage(params);

        return R.data(page);
    }

}
