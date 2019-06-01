package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
	public static void main(String[] args) {
		//下面两行代码都可以用来启动
        SpringApplication.run(EurekaApplication.class, args);
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
