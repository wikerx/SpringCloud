package com.scott.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @CLASSNAME :TestController
 * @Description :测试控制中心
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  17:11
 * @Version :V1.0
 * @Status : 编写
 **/
public class TestController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String Test(){
        ServiceInstance instance=client.getLocalServiceInstance();
        System.out.println("/test,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello world!";
    }

}
