package cn.moxi.push.web;

import cn.caipiaoq.push.web.WsController;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class WsControllerTest {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    public MockMvc mockMvc;

    @Autowired
    private WsController wsController;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(wsController).build();
    }

    @Test
    public void test_sendMessageToUser() throws Exception {
        wsController.handleChat("2","你的订单已发货!");
    }
}
