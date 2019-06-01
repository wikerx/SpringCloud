package com.zmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PaymentEurekaApplication {
/*服务注册中心*/
	public static void main(String[] args) {
		SpringApplication.run(PaymentEurekaApplication.class, args);
	}

}
