package com.yb.mall.common.mq.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
* @Description: Rocketmq基本配置
* @author cw
* @date 2019/1/10 14:44
*/
@ConfigurationProperties("rocketmq")
@Data
public final class RocketMqProperties {
    /**
     * 每个Broker启动的时候会向Namesrv发送注册请求
     * NameServer用来保存所有broker的Filter列表
     */
    private String nameSrvAddr = "localhost:9876";

    /**
     * 消费线程最小线程数
     */
    private int consumeThreadMin = 20;
    /**
     * 消费线程的最大线程数
     */
    private int consumeThreadMax = 64;

}
