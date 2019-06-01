package com.yb.mall.property.client.service.controller;


import com.alibaba.rocketmq.common.message.Message;
import com.yb.mall.common.mq.api.core.producer.MessageProxy;
import com.yb.mall.common.mq.api.core.producer.RocketMqProducerTemplate;
import com.yb.mall.property.client.service.service.ITbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;

import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cw
 * @since 2019-02-13
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {

    private final RocketMqProducerTemplate rocketMqProducerTemplate;
    private final ITbItemService iTbItemService;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Autowired
    public TbUserController(RocketMqProducerTemplate rocketMqProducerTemplate, ITbItemService iTbItemService) {
        this.rocketMqProducerTemplate = rocketMqProducerTemplate;
        this.iTbItemService = iTbItemService;
    }

    @GetMapping("/getiC")
    public String get (){
        String str= "{'admin':'root','name':'chenwu'}";
        Message msg = new Message("MY-TOPIC","TAG_1",str.getBytes());
        MessageProxy mp = new MessageProxy();
        mp.setMessage(msg);
        try {
            rocketMqProducerTemplate.send(mp);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @GetMapping("/getiS")
    public String getiS (){
        String str= "{'admin':'root','name':'cw'}";
        Message msg = new Message("MY-TOPIC","TAG_2",str.getBytes());
        MessageProxy mp = new MessageProxy();
        mp.setMessage(msg);
        try {
            rocketMqProducerTemplate.send(mp);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/hi")
    public String callHome(){
        return restTemplate.getForObject("http://localhost:8088/search/hi", String.class);
    }

    @RequestMapping("/his")
    public String his(){
        return "YES OK";
    }

}

