package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :RibbonConsumerApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  17:40
 * @Version :V1.0
 * @Status : 编写
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    //开启客户端负载均衡
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}