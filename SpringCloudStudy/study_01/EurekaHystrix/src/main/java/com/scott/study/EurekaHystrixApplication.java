package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :EurekaHystrixApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  15:29
 * @Version :V1.0
 * @Status : 编写
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix//熔断器
public class EurekaHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaHystrixApplication.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
