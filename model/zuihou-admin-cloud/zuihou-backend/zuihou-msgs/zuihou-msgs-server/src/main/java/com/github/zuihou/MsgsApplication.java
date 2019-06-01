package com.github.zuihou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zuihou
 * @createTime 2018-01-22 22:52
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan({
        "com.github.zuihou",
        "com.github.zuihou.commons.context",
})
@EnableTransactionManagement
@EnableFeignClients(
)
public class MsgsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgsApplication.class, args);
    }

}
