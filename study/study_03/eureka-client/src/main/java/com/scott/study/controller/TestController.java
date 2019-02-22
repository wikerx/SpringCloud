package com.scott.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :TestController
 * @Description :测试工具类
 * @Author :Mr.薛
 * @Data :2019/2/22 0022  10:15
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {
    Log log = LogFactory.getLog(TestController.class);
    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/test")
    public String Test(){
        log.info("使用端口："+port);
        ServiceInstance instance = client.getLocalServiceInstance();
        log.info("/hello, host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        System.out.println("host:"+client.getLocalServiceInstance().getHost()+"   -----port:"+client.getLocalServiceInstance().getPort());
        return "Hello SpringCloud~HOST:" + instance.getHost() + ",PORT:" + port + ",service_id:" + instance.getServiceId();
//        return "{\"people\":[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\"},{\"firstName\":\"Jason\",\"lastName\":\"Hunter\"}]}";
    }
}
