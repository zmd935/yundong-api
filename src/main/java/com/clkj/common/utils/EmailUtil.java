package com.clkj.common.utils;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Created by YangLiu on 2020-11-19
 */
@Slf4j
@Component
public class EmailUtil {
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.enable}")
    boolean enable;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public static final String RESET_PASSWORD = "/email/resetPassword.html";
    public static final String EMAIL_ICON = "/email_icon.png";


    /**
     * 发送纯文本邮件
     *
     * @param to       收件人邮箱
     * @param subject  标题
     * @param template 使用的html模板
     * @param params   模板中的替换参数
     */
    public boolean sendHtmlMail(String to, String subject, Map<String, String> params, String template) {
        if (!enable) {
            log.info("模拟发送邮件成功：" + to, JSONObject.toJSONString(params));
            return true;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            ClassPathResource cpr = new ClassPathResource(template);
            String content = IoUtil.read(cpr.getInputStream(), "UTF-8");
            for (Map.Entry<String, String> next : params.entrySet()) {
                content = content.replace(next.getKey(), next.getValue());
            }
            // 替换网站logo
            content = content.replace("${email_icon}", EMAIL_ICON);

            // true 为 HTML 邮件
            messageHelper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return false;
        }
        log.info("HTML邮件成功发送！to={}", to);

        return true;
    }

    /**
     * todo 还有问题
     * 发送纯带图片邮件
     *
     * @param to       收件人邮箱
     * @param subject  标题
     * @param template 使用的html模板
     * @param params   模板中的替换参数
     */
    public boolean sendImageMail(String to, String subject, Map<String, String> params, String template, Map<String, String> imageParams) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            ClassPathResource cpr = new ClassPathResource(template);
            String content = IoUtil.read(cpr.getInputStream(), "UTF-8");
            for (Map.Entry<String, String> next : params.entrySet()) {
                content = content.replace(next.getKey(), next.getValue());
            }

            messageHelper.setText(content, true);
            // 添加图片
            for (Map.Entry<String, String> entry : imageParams.entrySet()) {
                File file = new ClassPathResource(entry.getValue()).getFile();
                FileSystemResource fileResource = new FileSystemResource(file);
                if (fileResource.exists()) {
                    messageHelper.addInline(entry.getKey(), fileResource);
                }
            }
            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return false;
        }

        log.info("邮件成功发送！to={}", to);
        return true;
    }

}
