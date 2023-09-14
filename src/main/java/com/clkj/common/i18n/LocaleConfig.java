package com.clkj.common.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

/**
 * MVC配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class LocaleConfig {
    @Value("${spring.messages.basename}")
    private String basename;

    @Value("${spring.messages.encoding}")
    private String encoding;

    /**
     * 配置自己的国际化语言解析器
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


    /**
     * yml文件的配置没有作用,这里配置是为了注入工具类
     *
     * @return
     */
    @Bean
    public MessageSource getMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding(encoding);
        resourceBundleMessageSource.setBasenames(basename);

        Message.setMessageSource(resourceBundleMessageSource);
        return resourceBundleMessageSource;
    }
}
