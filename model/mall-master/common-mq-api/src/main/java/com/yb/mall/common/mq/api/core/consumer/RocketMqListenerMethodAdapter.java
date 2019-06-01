package com.yb.mall.common.mq.api.core.consumer;


import com.yb.mall.common.mq.api.RocketMqConsumerListener;
import com.yb.mall.common.mq.api.annotation.RocketListeners;
import com.yb.mall.common.mq.api.annotation.RocketMQListener;
import com.yb.mall.common.mq.api.exception.ConsumeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/*
* @Description: 监听到消息 处理本系统业务逻辑
* @author cw
* @date 2019/1/10 16:08
*/
public final class RocketMqListenerMethodAdapter<E> implements RocketMqConsumerListener<E> {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqListenerMethodAdapter.class);

    private final SubscriptionGroup subscriptionGroup;

    private ConsumerConfig consumerConfig;


    RocketMqListenerMethodAdapter(SubscriptionGroup subscriptionGroup) {
        this.subscriptionGroup = subscriptionGroup;
        initConfig(subscriptionGroup);
    }

    private void initConfig(SubscriptionGroup subscriptionGroup) {
        RocketListeners rocketListeners = subscriptionGroup.getTarget().getClass().getAnnotation(RocketListeners.class);
        consumerConfig = ConsumerConfig.builder()
                .consumerGroup(rocketListeners.consumerGroup())
                .messageModel(rocketListeners.messageModel())
                .orderlyMessage(rocketListeners.orderly())
                .topic(rocketListeners.topic())
                .consumeThreadMax(rocketListeners.consumeThreadMax())
                .consumeThreadMin(rocketListeners.consumeThreadMin())
                .build();
        Map<String, Class<?>> tags = new HashMap<>();
        subscriptionGroup.getTagList().forEach(tag -> {
            RocketMQListener rocketMQListener = subscriptionGroup.getMethod(tag).getAnnotation(RocketMQListener.class);
            tags.put(tag, rocketMQListener.messageClass());
        });
        consumerConfig.setTags(tags);
    }


    /**
     * 获取监听的消息 反射获取
     * @param message
     * @param context
     * @throws ConsumeException
     */
    @Override
    public void onMessage(E message, MessageContext context) throws ConsumeException {
        logger.info("recived message:{}", message);
        String tag = context.getMessageExt().getTags();
        Method method = this.subscriptionGroup.getMethod(tag);
        Object delegate = this.subscriptionGroup.getTarget();
        if (method != null) {
            try {
                method.invoke(delegate, message.toString());
            } catch (Exception e) {
                throw new ConsumeException(e);
            }
        } else {
            if(StringUtils.isNotEmpty(tag)){
                if (("*").equals(tag.trim())) {
                    //暂且不支持
                } else {
                    throw new ConsumeException("未找到相应tag的方法");
                }
            }
        }

    }

    @Override
    public ConsumerConfig getConsumerConfig() {
        return this.consumerConfig;
    }


}
