package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("custom")
public class CustomEnvironmentRepositoryConfiguration {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CustomEnvironmentRepositoryConfiguration.class);

    @Value("${spring.cloud.config.server.custom.order:0x7fffffff}")
    private int order;

    @Bean
    @ConditionalOnMissingBean(CustomZuulEnvironmentRepository.class)
    CustomZuulEnvironmentRepository customEnvironmentRepository() {

        CustomZuulEnvironmentRepository repo = new CustomZuulEnvironmentRepository();
        repo.setOrder(order);
        LOGGER.info("################################ " + repo);
        return repo;
    }
}
