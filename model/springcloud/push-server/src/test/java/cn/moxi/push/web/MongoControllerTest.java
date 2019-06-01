package cn.moxi.push.web;

import cn.caipiaoq.push.entity.Baike;
import cn.caipiaoq.push.entity.Comment;
import cn.caipiaoq.push.web.MongoController;
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

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class MongoControllerTest {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public MockMvc mockMvc;

    @Autowired
    private MongoController mongoController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mongoController).build();
    }

    @Test
    public void testadd() throws Exception {
        for(int i=21;i<= 25;i++){
            Baike baike = new Baike();
            baike.setId("beetlsql"+i);
            baike.setDesc("dao工具");
            baike.setTag(Arrays.asList("spring boot","spring cloud"));
            Comment comment = new Comment();
            comment.setGood(100+i);
            comment.setBad(11+i);
            baike.setComment(comment);
            Baike add = mongoController.add(baike);
            logger.info("baike add:{}",JSON.toJSONString(add));
        }

    }

    @Test
    public void testdelete() throws Exception {
        Baike delete = mongoController.delete("5bb494597b14c25a2366ff85");
        logger.info("baike delete :{}",JSON.toJSONString(delete));
    }

    @Test
    public void test_addOne() throws Exception {
        String it = mongoController.addOne("IT");
        logger.info("baike delete :{}",JSON.toJSONString(it));
    }

    @Test
    public void test_findUser() throws Exception {
        Baike it = mongoController.findUser("beetlsql");
        logger.info("baike delete :{}",JSON.toJSONString(it));
    }

    @Test
    public void test_queryBad() throws Exception {
        List<Baike> baikes = mongoController.queryBad(30);
        logger.info("baike queryBad :{}",JSON.toJSONString(baikes));
    }

    @Test
    public void test_findBaike() throws Exception {
        List<Baike> it = mongoController.findBaike("spring boot", 1);
        logger.info("baike findBaike :{}",JSON.toJSONString(it));
    }
}
