package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASSNAME :EurukaServerApplication
 * @Description :注册中心
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  16:51
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurukaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurukaServerApplication.class, args);
    }
}