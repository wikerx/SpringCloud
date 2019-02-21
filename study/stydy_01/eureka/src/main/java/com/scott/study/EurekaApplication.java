package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASSNAME :com.scott.study.EurekaApplication
 * @Description :注册中心
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  12:05
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
