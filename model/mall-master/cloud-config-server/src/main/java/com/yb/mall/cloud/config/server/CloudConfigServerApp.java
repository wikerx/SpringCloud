package com.yb.mall.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
* @Description: cloud-config 启动类
* @author cw
* @date 2019/1/7 15:54
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class CloudConfigServerApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApp.class, args);
    }
}