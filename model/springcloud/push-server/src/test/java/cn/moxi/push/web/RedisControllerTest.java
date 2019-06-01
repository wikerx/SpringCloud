package cn.moxi.push.web;

import cn.caipiaoq.push.constant.ChannelConstant;
import cn.caipiaoq.push.web.RedisController;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class RedisControllerTest {

    public final Logger logger = LoggerFactory.getLogger(getClass());
    public MockMvc mockMvc;

    @Autowired
    private RedisController redisController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(redisController).build();
    }

    @Test
    public void test_getset() throws Exception {
        String getset = redisController.getset("test", "spring cloud redis");
        logger.info("getset:{}",getset);
    }

    @Test
    public void test_leftPush() throws Exception {
        String s = redisController.leftPush("platform:message", "hello,world");
        logger.info("leftPush:{}",s);
    }

    @Test
    public void test_boundListOps() throws Exception {
        String s = redisController.boundListOps("somekey", "a","b","c");
        logger.info("boundListOps:{}",s);
    }

    @Test
    public void test_range() throws Exception {
        List<String> range = redisController.range("platform:message", 0, -1);
        logger.info("leftPush:{}",JSON.toJSONString(range));
    }

    @Test
    public void test_hset() throws Exception {
        redisController.hset("cache", "key1", "value1");
        redisController.hset("cache", "key2", "value2");
        logger.info("hash put key1 value1,key2 value2 success");
    }

    @Test
    public void test_get() throws Exception {
        Object value1 = redisController.hget("cache", "key1");
        Object value2 = redisController.hget("cache", "key2");
        logger.info("hash get key1:{} ,key2:{}  success",JSON.toJSONString(value1), JSON.toJSONString(value2));
    }

    @Test
    public void test_pub() throws Exception {
        redisController.pub(ChannelConstant.CHANNEL_NEW,"hello news1");
    }
}
