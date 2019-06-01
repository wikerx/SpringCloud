package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class ConfigServerDbApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConfigServerDbApplication.class);
        application.run(args);
    }

}
