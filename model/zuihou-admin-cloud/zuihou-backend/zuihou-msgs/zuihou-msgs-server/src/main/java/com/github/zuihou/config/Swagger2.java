package com.github.zuihou.config;


import com.github.zuihou.commons.adapter.Swagger2WebMvcConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 *
 * @author xubin.
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
    public Docket createMsgsPubApi() {
        return getDefDocket("msgs", "priMsgs");
    }

    @Bean
    public Docket createMsgsCenterPriApi() {
        return getDefDocket("msgsCenter", "priMsgsCenter");
    }

    @Bean
    public Docket createSmsInsideApi() {
        return getDefDocket("sms", "priSms");
    }


    /**
     * 覆盖父类的拦截器
     *
     * @return
     */
//    @Bean
//    public HandlerInterceptor getHandlerInterceptor() {
//        return new AuthClientContextHandlerInterceptor();
//    }

}
