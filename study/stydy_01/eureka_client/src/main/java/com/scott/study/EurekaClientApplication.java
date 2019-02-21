package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @CLASSNAME :EurekaClientApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  12:06
 * @Version :V1.0
 * @Status : 编写
 **/
@ComponentScan("com.scott.study.controller")
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
