package net.getbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class CodeGanerationApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CodeGanerationApplication.class, args);
		
	}
	
}
