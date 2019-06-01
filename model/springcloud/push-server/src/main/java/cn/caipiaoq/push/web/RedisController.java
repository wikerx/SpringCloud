package cn.caipiaoq.push.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/getset")
    public String getset(String key, String value) {
        LOGGER.info("getset key:{}, value:{}",key, value);
        redisTemplate.opsForValue().set(key,value);
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/leftPush")
    public String leftPush(String key, String value){
        LOGGER.info("list key:{}, value:{}",key, value);
        Long aLong = redisTemplate.opsForList().leftPush(key, value);
        return "成功添加："+aLong;
    }

    @GetMapping("/boundListOps")
    public String boundListOps(String key, String...s){
        LOGGER.info("boundListOps key:{}, s:{}",key, s);
        BoundListOperations<String, String> ops = redisTemplate.boundListOps(key);
        for (String str:s){
            ops.leftPush(str);
        }
        return String.valueOf(ops.size());
    }

    @GetMapping("/range")
    public List<String> range(String key, long begin, long end){
        LOGGER.info("list key:{}, value:{}, end:{}",key, begin, end);
        List<String> range = redisTemplate.opsForList().range(key, begin, end);
        return range;
    }

    @GetMapping("/hset")
    public void hset(String h, Object hk, Object hv){
        LOGGER.info("hset h:{}, hk:{}, hv:{}",h, hk, hv);
        redisTemplate.opsForHash().put(h, hk, hv);
    }

    @GetMapping("/hget")
    public Object hget(String h, Object hk){
        LOGGER.info("hget h:{}, hk:{}",h, hk);
        Object o = redisTemplate.opsForHash().get(h, hk);
        return o;
    }

    //发布一条消息
    @GetMapping("/pub")
    public void pub(String channel,String message) {
        LOGGER.info("pub channel:{}, message:{}",channel, message);
        redisTemplate.convertAndSend(channel,message);
    }

    
}
