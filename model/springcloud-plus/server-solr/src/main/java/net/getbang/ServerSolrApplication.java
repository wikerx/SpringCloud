package net.getbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
@SpringBootApplication
public class ServerSolrApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ServerSolrApplication.class, args);
	}
}
