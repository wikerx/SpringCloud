package cn.moxi.push.web;

import cn.caipiaoq.push.entity.BookEntity;
import cn.caipiaoq.push.web.ElasticsearchController;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ElasticsearchControllerTest {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public MockMvc mockMvc;

    @Autowired
    private ElasticsearchController elasticsearchController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(elasticsearchController).build();
    }

    @Test
    public void test_add() throws Exception {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setId("1");
//        bookEntity.setMessage("spring cloud实战");
//        bookEntity.setName("spring cloud");
//        bookEntity.setType("1");
//        bookEntity.setPostDate(new Date());
//
//        BookEntity bookEntity2 = new BookEntity();
//        bookEntity2.setId("2");
//        bookEntity2.setMessage("从构建小系统到架构分布式大系统");
//        bookEntity2.setName("spring boot2精髓");
//        bookEntity2.setType("2");
//        bookEntity2.setPostDate(new Date());

        BookEntity bookEntity3 = new BookEntity();
        bookEntity3.setId("3");
        bookEntity3.setMessage("Java EE开发大颠覆者");
        bookEntity3.setName("spring boot 实战");
        bookEntity3.setType("2");
        bookEntity3.setPostDate(new Date());

        BookEntity entity2 = elasticsearchController.add(bookEntity3);
        logger.info("add:{},{}", JSON.toJSONString(entity2));
    }

    @Test
    public void test_delete() throws Exception {
        BookEntity book = elasticsearchController.delete("1");
        logger.info("delete:{}", JSON.toJSONString(book));
    }

    @Test
    public void test_update() throws Exception {
        BookEntity bookEntity = elasticsearchController.getBookById("1");
        bookEntity.setId("1");
        bookEntity.setName("spring boot2 精髓update");
        bookEntity.setPostDate(new Date());
        BookEntity entity = elasticsearchController.update(bookEntity);
        logger.info("update:{}",JSON.toJSONString(entity));
    }

    @Test
    public void test_getBookById() throws Exception {
        BookEntity bookEntity = elasticsearchController.getBookById("1");
        logger.info("getBookById:{}", JSON.toJSONString(bookEntity));
    }

    @Test
    public void test_getBookById2() throws Exception {
        BookEntity bookEntity = elasticsearchController.getBookById2("1");
        logger.info("getBookById2:{}", JSON.toJSONString(bookEntity));
    }

    @Test
    public void test_search() throws Exception {
        List<BookEntity> list = elasticsearchController.search("系统");
        logger.info("search:{}", JSON.toJSONString(list));
    }

    @Test
    public void test_searchQ() throws Exception {
        List<BookEntity> list = elasticsearchController.searchQ("精髓");
        logger.info("searchQ:{}", JSON.toJSONString(list));
    }

    @Test
    public void test_searchPage() throws Exception {
        List<BookEntity> list = elasticsearchController.searchPage(1,10,"java");
        logger.info("searchPage:{}", JSON.toJSONString(list));
    }

    @Test
    public void test_searchMessagePage() throws Exception {
        List<BookEntity> list = elasticsearchController.searchMessagePage("spring cloud实战",1);
        logger.info("searchMessagePage :{}", JSON.toJSONString(list));
    }

    @Test
    public void test_searchAll() throws Exception {
        List<Map<String, Object>> maps = elasticsearchController.searchAll();
        logger.info("searchAll :{}", JSON.toJSONString(maps));
    }
}
