package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @CLASSNAME :EurekalclientApplication
 * @Description :客户端
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  17:09
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class EurekalclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekalclientApplication.class, args);
    }
}
