package com.clkj.modules.sys.service;

import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author clkj
 */
public interface SysCaptchaService {

    /**
     * 获取图片验证码
     *
     * @param uuid uuid
     * @return BufferedImage
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     *
     * @param uuid uuid
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
