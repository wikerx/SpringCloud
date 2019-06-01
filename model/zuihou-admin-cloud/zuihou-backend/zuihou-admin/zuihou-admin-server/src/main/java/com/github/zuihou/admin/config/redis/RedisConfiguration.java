package com.github.zuihou.admin.config.redis;

import com.github.zuihou.cache.configure.BaseRedisConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
