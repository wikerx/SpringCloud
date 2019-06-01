package com.yb.mall.common.mq.api;


import com.yb.mall.common.mq.api.core.consumer.ConsumerConfig;
import com.yb.mall.common.mq.api.core.consumer.MessageContext;
import com.yb.mall.common.mq.api.exception.ConsumeException;

/**
 * 消息监听
 * @param <E>
 */
public interface RocketMqConsumerListener<E> {

    void onMessage(E message, MessageContext messageContext) throws ConsumeException;

    ConsumerConfig getConsumerConfig();


}
