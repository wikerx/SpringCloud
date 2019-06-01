package com.scott.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASSNAME :EurekaServerApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/28  15:58
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    /*启动服务*/
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
