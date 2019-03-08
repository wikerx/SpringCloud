package com.scott.study.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scott.study.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :HystrixServiceImpl
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  15:36
 * @Version :V1.0
 * @Status : 编写
 **/
@Service
public class HystrixServiceImpl implements HystrixService{
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "isError")
    public String success() {
        /*向注册中心发布数据信息*/
        return restTemplate.getForObject("http://STUDYNO2/test",String.class);
    }

    public String isError() {
        return "hi,sorry,error!";
    }

}
