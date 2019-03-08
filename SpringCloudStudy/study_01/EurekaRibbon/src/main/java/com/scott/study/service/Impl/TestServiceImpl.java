package com.scott.study.service.Impl;

import com.scott.study.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :TestServiceImpl
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  10:54
 * @Version :V1.0
 * @Status : 编写
 **/
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    RestTemplate restTemplate;

    /*测试*/
    public String test(String name){
//        向注册中心发送数据访问请求
        return restTemplate.getForObject("http://STUDYNO1/data?name="+name,String.class);
    }

    /*测试*/
    public String test1(){
//        向注册中心发送数据访问请求
        return restTemplate.getForObject("http://STUDYNO1/test",String.class);
    }

    /*测试*/
    public String testStudy01(){
//        向注册中心发送数据访问请求
        return restTemplate.getForObject("http://STUDYNO2/test",String.class);
    }

}
