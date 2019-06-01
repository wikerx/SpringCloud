package com.service.impl;

import com.config.RedisConfig;
import com.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            logger.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }
    
    /**
     * 
     * @param key 
     * @param value
     * @param seconds 过期时间（秒）
     */
    @Override
    public void set(String key, String value,int seconds) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            jedis.expire(key, seconds);
            logger.info("Redis set success - " + key + ", value:" + value+",expire:"+seconds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
            logger.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

	@Override
	public long incr(String key) {
		Jedis jedis=null;
        try{
            jedis = getResource();
            Long incr = jedis.incr(key);
            logger.info("Redis incr key success - " + key);
            return incr;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis incr key error: "+ e.getMessage() +" - " + key);
        }finally{
            returnResource(jedis);
        }
		return 0;
	}

	@Override
	public void removeKey(String key) {
		Jedis jedis=null;
        try{
            jedis = getResource();
            Long i = jedis.del(key);
            if(i==1){
            	logger.info("Redis remove key success - " + key);
            }else {
            	logger.info("Redis remove key fail - " + key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis incr key error: "+ e.getMessage() +" - " + key);
        }finally{
            returnResource(jedis);
        }
	}

	@Override
	public void hset(String key,String field,String value){
        Jedis jedis=null;
        try{
            jedis = getResource();
            Long i = jedis.hset(key,field,value);
            if(i==1){
                logger.info("Redis hset key success - " + key);
            }else if(i==0) {
                logger.info("Redis hset key overwrite old field success - " + key);
            }else {
                logger.info("Redis hset key fail -"+key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis incr key error: "+ e.getMessage() +" - " + key);
        }finally{
            returnResource(jedis);
        }
    }
	
    
}
