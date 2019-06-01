package com.yb.mall.common.mq.api.annotation;


import java.lang.annotation.*;

/*
* @Description: 改注解运用在消费端的方法上，用来处理同一topic中不同的tag类型的消息
* @author cw
* @date 2019/1/10 16:09
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMQListener {

    /**
     * 订阅的tag
     */
    String tag() default "*";

    /**
     * 请求方消息类型
     */
    Class<?> messageClass() default Object.class;
}
