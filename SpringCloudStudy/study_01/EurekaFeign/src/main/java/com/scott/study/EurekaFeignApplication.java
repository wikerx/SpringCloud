package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @CLASSNAME :EurekaFeignApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  14:14
 * @Version :V1.0
 * @Status : 编写
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignApplication.class,args);
    }
}
