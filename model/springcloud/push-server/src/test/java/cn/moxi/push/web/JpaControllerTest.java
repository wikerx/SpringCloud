package cn.moxi.push.web;

import cn.caipiaoq.push.entity.User;
import cn.caipiaoq.push.web.JpaController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class JpaControllerTest {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public MockMvc mockMvc;

    @Autowired
    private JpaController jpaController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(jpaController).build();
    }

    //@Test
    public void test_add() throws Exception {
        for (int i=15;i<26;i++){
            User user1 = new User();
            user1.setName("汪云飞");
            user1.setSex(1);
            user1.setAge(i+10);
            user1.setDepartmentId(i);
            user1.setCreateTime(new Date());
            jpaController.add(user1);
        }
    }

    //@Test
    public void test_delete() throws Exception {
        jpaController.delete(1);
    }

    //@Test
    public void test_update() throws Exception {
        User user = new User();
        user.setName("付磊update");
        user.setDepartmentId(1);
        user.setAge(30);
        user.setSex(1);
        user.setCreateTime(new Date());
        jpaController.update(user);
    }

    //@Test
    public void test_findById() throws Exception {
        User user = jpaController.findById(11);
        logger.info("findById:{}",JSON.toJSONString(user,SerializerFeature.WriteNullStringAsEmpty));
    }

    //@Test
    public void test_findByName() throws Exception {
        User user = jpaController.findByName("付磊");
        logger.info("findByName:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_getById() throws Exception {
        User user = jpaController.getById(11);
        logger.info("getById:{}",JSON.toJSONString(user,SerializerFeature.WriteNullStringAsEmpty));
    }

    //@Test
    public void test_getByName() throws Exception {
        User user = jpaController.getByName("付磊");
        logger.info("getByName:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_queryById() throws Exception {
        User user = jpaController.queryById(11);
        logger.info("queryById:{}",JSON.toJSONString(user,SerializerFeature.WriteNullStringAsEmpty));
    }

    //@Test
    public void test_queryByName() throws Exception {
        User user = jpaController.queryByName("付磊");
        logger.info("queryByName:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_findByNameAndDepartmentId() throws Exception {
        List<User> users = jpaController.findByNameAndDepartmentId("汪云飞",1);
        logger.info("findByNameAndDepartmentId:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByNameLike() throws Exception {
        List<User> users = jpaController.findByNameLike("汪云飞");
        logger.info("findByNameLike:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByNameNotLike() throws Exception {
        List<User> users = jpaController.findByNameNotLike("汪云飞");
        logger.info("findByNameNotLike:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByNamePage() throws Exception {
        List<User> users = jpaController.findByNamePage("汪云飞", 1,5);
        logger.info("findByNamePage:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findFirst10ByName() throws Exception {
        List<User> users = jpaController.findFirst10ByName("汪云飞");
        logger.info("findFirst10ByName:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findDistinctUserByNameOrDepartmentId() throws Exception {
        List<User> users = jpaController.findDistinctUserByNameOrDepartmentId("李家智", 15);
        logger.info("findDistinctUserByNameOrDepartmentId:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByCreateTimeBetween() throws Exception {
        List<User> users = jpaController.findByCreateTimeBetween("2018-10-07 00:00:00", "2018-10-08 00:00:00");
        logger.info("findByCreateTimeBetween:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeLessThan() throws Exception {
        List<User> users = jpaController.findByAgeLessThan(18);
        logger.info("findByAgeLessThan:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeLessThanEqual() throws Exception {
        List<User> users = jpaController.findByAgeLessThanEqual(20);
        logger.info("findByAgeLessThanEqual:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeGreaterThan() throws Exception {
        List<User> users = jpaController.findByAgeGreaterThan(23);
        logger.info("findByAgeGreaterThan:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeGreaterThanEqual() throws Exception {
        List<User> users = jpaController.findByAgeGreaterThanEqual(25);
        logger.info("findByAgeGreaterThanEqual:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByCreateTimeAfter() throws Exception {
        List<User> users = jpaController.findByCreateTimeAfter("2018-10-07 00:00:00");
        logger.info("findByCreateTimeAfter:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByCreateTimeBefore() throws Exception {
        List<User> users = jpaController.findByCreateTimeBefore("2018-10-06 10:30:00");
        logger.info("findByCreateTimeBefore:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeIsNull() throws Exception {
        List<User> users = jpaController.findByAgeIsNull();
        logger.info("findByAgeIsNull:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeNotNull() throws Exception {
        List<User> users = jpaController.findByAgeNotNull();
        logger.info("findByAgeNotNull:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeIsNotNull() throws Exception {
        List<User> users = jpaController.findByAgeIsNotNull();
        logger.info("findByAgeIsNotNull:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeOrderByNameDesc() throws Exception {
        List<User> users = jpaController.findByAgeOrderByNameDesc(18);
        logger.info("findByAgeOrderByNameDesc:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeIn() throws Exception {
        Set<Integer> ages = new HashSet<>();
        ages.add(new Integer(16));
        ages.add(new Integer(17));
        ages.add(new Integer(18));
        ages.add(new Integer(19));
        List<User> users = jpaController.findByAgeIn(ages);
        logger.info("findByAgeIn:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findByAgeNotIn() throws Exception {
        Set<Integer> ages = new HashSet<>();
        ages.add(new Integer(18));
        List<User> users = jpaController.findByAgeNotIn(ages);
        logger.info("findByAgeNotIn:{}",JSON.toJSONString(users));
    }

    //@Test
    public void test_findUserQuery() throws Exception {
        User user = jpaController.findUserQuery("汪云飞",25);
        logger.info("findUserQuery:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_findUserNativeQuery() throws Exception {
        Set<Integer> ages = new HashSet<>();
        ages.add(new Integer(18));
        User user = jpaController.findUserNativeQuery("汪云飞",25);
        logger.info("findUserNativeQuery:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_findUserQueryNameParam() throws Exception {
        Set<Integer> ages = new HashSet<>();
        ages.add(new Integer(18));
        User user = jpaController.findUserQueryNameParam("汪云飞",25);
        logger.info("findUserQueryNameParam:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_findUserNativeQueryNameParam() throws Exception {
        User user = jpaController.findUserNativeQueryNameParam("汪云飞",25);
        logger.info("findUserNativeQueryNameParam:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_queryUserCount() throws Exception {
        Set<Integer> ages = new HashSet<>();
        ages.add(new Integer(18));
        List<Object[]> user = jpaController.queryUserCount();
        logger.info("queryUserCount:{}",JSON.toJSONString(user));
    }

    @Test
    public void test_queryUsers() throws Exception {
        List<User> user = jpaController.queryUsers(1,0,10);
        logger.info("queryUsers:{}",JSON.toJSONString(user));
    }

    //@Test
    public void test_updateName() throws Exception {
        User user = jpaController.updateName("秦小波",5);
        logger.info("updateName:{}",JSON.toJSONString(user));
    }

}
