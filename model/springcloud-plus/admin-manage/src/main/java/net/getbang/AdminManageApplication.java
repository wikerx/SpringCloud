package net.getbang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("net.getbang.project.*.*.mapper")
public class AdminManageApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AdminManageApplication.class, args);
	}
}
