package com.clkj.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Created by clkj on 2021-04-02
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(Environment environment) {
        Profiles profiles = Profiles.of("dev", "test");
        boolean enable = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                //.apis(RequestHandlerSelectors.basePackage("com.clkj.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("辰领科技")
                .description("辰领科技文档")
                .termsOfServiceUrl("http://www.lingkj.com")
                .version("1.0.0")
                .build();
    }

    private List<SecurityScheme> security() {
        return newArrayList(
                new ApiKey("user-token", "user-token", "header")
        );
    }

}
