package com.github.zuihou.file.configuration.dozer;


import com.github.dozermapper.core.Mapper;
import com.github.zuihou.commons.context.DozerUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerAutoConfiguration {

    @Bean
    public DozerUtils getDozerUtils(Mapper mapper){
        return new DozerUtils(mapper);
    }
}
