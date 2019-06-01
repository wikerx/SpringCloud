package com.yb.mall.property.client.service.listener;

import com.yb.mall.common.mq.api.annotation.RocketListeners;
import com.yb.mall.common.mq.api.annotation.RocketMQListener;

/*
* @Description: TODO
* @author cw
* @date 2019/1/14 13:58
*/
@RocketListeners(topic = "MY-TOPIC")
public class MyListtener {
    @RocketMQListener(messageClass = String.class,tag = "TAG_1")
    public void method1(String message){
        System.out.println(message+"___________sadasdsad_________");
    }

    @RocketMQListener(messageClass = String.class,tag = "TAG_2")
    public void method2(String message){
        System.out.println(message+"___________1111111123123123123123_________");
    }


}
