package com.service;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
public interface RedisService {
    public Jedis getResource();

    public void returnResource(Jedis jedis);

    public void set(String key, String value);
    
    public void set(String key, String value, int seconds);

    public String get(String key);

    public long incr(String key);

    public void removeKey(String key);

    public void hset(String key, String field, String value);
}
