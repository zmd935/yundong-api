package com.clkj.modules.sys.service.impl;

import com.clkj.common.i18n.Message;
import com.clkj.common.utils.RedisKeys;
import com.clkj.common.utils.RedisUtils;
import com.clkj.common.utils.StringUtils;
import com.clkj.common.validator.Assert;
import com.clkj.modules.sys.service.SysCaptchaService;
import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl implements SysCaptchaService {

    private final Producer producer;

    private final RedisUtils redisUtils;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        Assert.notBlank(uuid, Message.getMessage("admin.sys.login.uuid"));
        //生成文字验证码
        String code = producer.createText();
        String key = RedisKeys.getSysCaptchaKey(uuid);
        redisUtils.expire(key, code, 5, TimeUnit.MINUTES);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {

        String key = RedisKeys.getSysCaptchaKey(uuid);
        String storeCode = redisUtils.get(key);
        if (StringUtils.isBlank(storeCode)) {
            return false;
        }
        redisUtils.delete(key);
        return storeCode.equals(code);
    }
}
