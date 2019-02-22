package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :EurekaRibbonApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/2/22 0022  10:44
 * @Version :V1.0
 * @Status : 编写
 **/

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
