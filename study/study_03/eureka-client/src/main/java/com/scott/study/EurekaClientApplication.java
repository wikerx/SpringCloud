package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :EurekaClientApplication
 * @Description :客户端
 * @Author :Mr.薛
 * @Data :2019/2/22 0022  10:13
 * @Version :V1.0
 * @Status : 编写
 **/
@SpringBootApplication
@RestController
@EnableEurekaClient//启动注册中心客户端
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class,args);
    }
}
