package com.github.zuihou.file.configuration.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zuihou.cache.configure.BaseRedisConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author zuihou
 * @createTime 2017-12-22 17:52
 */
@Configuration
public class RedisConfiguration extends BaseRedisConfiguration {
    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(final RedisConnectionFactory connectionFactory) {
        return super.getRedisTemplate(connectionFactory);
    }

    @Bean("redisLongTemplate")
    public RedisTemplate redisLongTemplate(final RedisConnectionFactory connectionFactory) {
        return super.getRedisLongTemplate(connectionFactory);
    }

}
