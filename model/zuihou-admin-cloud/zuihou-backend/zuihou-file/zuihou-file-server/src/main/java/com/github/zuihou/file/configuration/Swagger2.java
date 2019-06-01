package com.github.zuihou.file.configuration;

import com.github.zuihou.commons.adapter.Swagger2WebMvcConfigurerAdapter;
import com.github.zuihou.commons.exception.core.ExceptionCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2的配置类
 *
 * @author zuihou.
 * @create 2017-04-08
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({Swagger2.Swagger2BaseProperties.class})
public class Swagger2 extends Swagger2WebMvcConfigurerAdapter {
    @Autowired
    Swagger2BaseProperties swagger2Properties;

    @Override
    protected Swagger2BaseProperties getSwagger2BaseProperties() {
        return swagger2Properties;
    }

    /**
     * 权限服务对外 swagger文档
     *
     * @return
     */
    @Bean
    public Docket createRestPriApi() {
        return getDefDocket("file", "priFile");
    }

}
