package com.yb.mall.property.client.service;

import com.yb.mall.common.mq.api.annotation.EnableRocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
* @Description: 商城客户端
* @author cw
* @date 2019/1/7 17:14
*/
@SpringCloudApplication
@EnableTransactionManagement
@MapperScan("com.yb.mall.property.client.service.mapper")
@ComponentScan(value ={ "com.yb.mall"})
@EnableFeignClients(basePackages = "com.yb.mall.common.feign.api")
@EnableRocket
public class PropertyClientServiceStart {
    public static void main(String[] args) {
        SpringApplication.run(PropertyClientServiceStart.class,args);
    }

    @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
