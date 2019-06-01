package com.github.zuihou.commons.queue;


import java.time.LocalDateTime;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/25
 */
@Slf4j
public abstract class BaseQueue {
    /**
     * 消息中心，保存消息
     */
    protected final static String MSGSCENTER_SAVE = ":msgscenter:save";
    /**
     * 消息中心，修改消息状态
     */
    protected final static String MSGSCENTER_UPDATE = ":msgscenter:update";


    @Value("${spring.rabbitmq.key-prefix}")
    protected String keyPrefix;
    @Autowired
    protected AmqpTemplate rabbitTemplate;

    protected String getRoutingKey(String key) {
        return keyPrefix + key;
    }

    protected void execute(String routingKey, String param) {
        try {
            if (routingKey == null || "".equals(routingKey) || param == null || "".equals(param)) {
                log.error("发送消息至队列异常时，必须填写正确的 路由key 和 参数. 目前传递的key={}, param={}", routingKey, param);
                return;
            }
            this.rabbitTemplate.convertAndSend(getRoutingKey(routingKey), param);
        } catch (Exception e) {
            log.warn("发送消息至队列异常", e);
        }
    }

    protected void execute(String param, Consumer<String> consumer) {
        try {
            log.info("当前时间：{}, param={}", LocalDateTime.now(), param);

            consumer.accept(param);
        } catch (Exception e) {
            log.warn("接收到消息队列中的消息，但执行失败", e);
        }
    }
}
