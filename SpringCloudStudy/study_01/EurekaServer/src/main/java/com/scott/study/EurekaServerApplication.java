package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASSNAME :EurekaServerApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  9:44
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableEurekaServer//注册中心
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
