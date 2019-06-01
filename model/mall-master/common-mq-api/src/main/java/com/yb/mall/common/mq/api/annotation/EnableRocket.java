package com.yb.mall.common.mq.api.annotation;

import com.yb.mall.common.mq.api.configuration.RocketMqAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/*
* @Description: 程序启用RocketMq
* @author cw
* @date 2019/1/10 14:21
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RocketMqAutoConfiguration.class)
public @interface EnableRocket {
}
