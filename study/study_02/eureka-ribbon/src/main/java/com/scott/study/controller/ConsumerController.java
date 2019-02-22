package com.scott.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @CLASSNAME :ConsumerController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  17:41
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://eureka-client/hello",String.class).getBody();
    }

}