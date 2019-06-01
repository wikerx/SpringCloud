package com.yb.mall.property.solr.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
 * @Description: 搜索服务启动器
 * @author cw
 * @date 2019/1/17 10:19
 */
@SpringCloudApplication
@EnableTransactionManagement
@MapperScan("com.yb.mall.property.solr.service.mapper")
@ComponentScan(value ={ "com.yb.mall"})
@EnableFeignClients(basePackages = "com.yb.mall.common.feign.api")
public class PropertySolrServiceStart {

    public static void main(String[] args) {
        SpringApplication.run(PropertySolrServiceStart.class,args);
    }

    @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
