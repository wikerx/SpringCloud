package net.getbang.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private RedisClient redisClient;
	
	
	@Test
	public void reclient() {
		
		redisClient.set("aa", "客户端测试", 2000);
		
		 System.out.println("从Redis中读取数据--------："+ redisClient.get("aa"));
		
	}
	
	public static void main(String[] args) {
		
		
		String  a ="2";
		
		Integer b = 2;
		
		System.out.println(a.equals(b));
	}
	
	
	@Test
	public void testaddredis() {
		
		redisTemplate.opsForValue().set("test-key", "redis测试内容", 2, TimeUnit.SECONDS);// 缓存有效期2秒
		
        System.out.println("从Redis中读取数据：" + redisTemplate.opsForValue().get("test-key").toString());
		
	}
	
	
	
	
	
}
