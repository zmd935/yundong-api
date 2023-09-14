package com.clkj.modules.sys.controller;

import com.clkj.common.i18n.Message;
import com.clkj.common.utils.PasswordUtil;
import com.clkj.common.utils.R;
import com.clkj.common.validator.Assert;
import com.clkj.modules.sys.entity.SysUserEntity;
import com.clkj.modules.sys.form.SysLoginForm;
import com.clkj.modules.sys.form.SysUserTokenForm;
import com.clkj.modules.sys.service.SysCaptchaService;
import com.clkj.modules.sys.service.SysUserService;
import com.clkj.modules.sys.service.SysUserTokenService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@RestController
public class SysLoginController extends AbstractController {

    private final SysUserService sysUserService;

    private final SysUserTokenService sysUserTokenService;

    private final SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public R<SysUserTokenForm> login(@RequestBody SysLoginForm form) throws IOException {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        Assert.beTrue(captcha, Message.getMessage("admin.sys.login.captcha"));

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !PasswordUtil.check(form.getPassword(), user.getSalt(), user.getPassword())) {
            return R.error(Message.getMessage("admin.sys.login.account"));
        }

        //账号锁定
        Assert.equalsTo(user.getStatus(), 1, Message.getMessage("admin.sys.login.locked"));

        //生成token，并保存到数据库
        return sysUserTokenService.createToken(user.getUserId());
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public R<?> logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }
}
