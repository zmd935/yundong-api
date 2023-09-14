/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.modules.sys.controller;


import com.clkj.common.annotation.SysLog;
import com.clkj.common.utils.PageUtils;
import com.clkj.common.utils.R;
import com.clkj.common.validator.ValidatorUtils;
import com.clkj.modules.sys.entity.SysConfigEntity;
import com.clkj.modules.sys.service.SysConfigService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {

    private final SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R<PageUtils<SysConfigEntity>> list(@RequestParam Map<String, Object> params) {
        PageUtils<SysConfigEntity> page = sysConfigService.queryPage(params);
        return R.data(page);
    }


    /**
     * 配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R<?> info(@PathVariable("id") Long id) {
        SysConfigEntity config = sysConfigService.getById(id);
        return R.data(config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R<?> save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.saveConfig(config);
        return R.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R<?> update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);
        sysConfigService.update(config);
        return R.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R<?> delete(@RequestBody Long[] ids) {
        sysConfigService.deleteBatch(ids);
        return R.ok();
    }
}
