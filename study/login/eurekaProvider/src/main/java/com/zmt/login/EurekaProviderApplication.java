package com.zmt.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @CLASSNAME :EurekaProviderApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/28  16:32
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.zmt.login.mapper")
public class EurekaProviderApplication {
    /*服务提供者*/
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class,args);
    }
}
