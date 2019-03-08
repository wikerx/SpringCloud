package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @CLASSNAME :EurekaClientApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  10:02
 * @Version :V1.0
 * @Status : 编写
 **/
@SpringBootApplication
@EnableEurekaClient//服务提供层
public class EurekaClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2Application.class,args);
    }
}
