/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.clkj.common.utils.R;
import com.clkj.modules.sys.entity.SysUserTokenEntity;
import com.clkj.modules.sys.form.SysUserTokenForm;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    R<SysUserTokenForm> createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);

}
