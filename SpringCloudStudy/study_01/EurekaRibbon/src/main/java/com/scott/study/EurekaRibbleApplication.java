package com.scott.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :EurekaRibbleApplication
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  10:50
 * @Version :V1.0
 * @Status : 编写
 **/

@SpringBootApplication
@EnableDiscoveryClient//服务消费者
public class EurekaRibbleApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbleApplication.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
