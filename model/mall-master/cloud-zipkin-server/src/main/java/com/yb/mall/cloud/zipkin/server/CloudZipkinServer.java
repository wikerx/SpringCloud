package com.yb.mall.cloud.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @Auther: cw
 * @Date: 2019/2/13 14:29 链路追踪服务
 * @Description:
 */
@SpringBootApplication
@EnableZipkinServer
public class CloudZipkinServer {

    public static void main(String[] args) {
        SpringApplication.run(CloudZipkinServer.class,args);
    }
}
