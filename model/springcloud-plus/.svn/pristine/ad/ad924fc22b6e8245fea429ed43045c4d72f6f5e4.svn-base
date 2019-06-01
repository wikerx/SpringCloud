package net.getbang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ConfigClientApplication {
	
		@Value("${server.port}")
	    Integer port2;

	    @RequestMapping("/")
	    public String home() {
	        return "port:" + port2;
	    }

	
	 public static void main(String[] args) {
	        SpringApplication.run(ConfigClientApplication.class, args);
	    }
}
