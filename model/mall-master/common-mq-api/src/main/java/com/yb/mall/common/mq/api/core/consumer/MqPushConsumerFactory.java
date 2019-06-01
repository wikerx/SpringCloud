package com.yb.mall.common.mq.api.core.consumer;


import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.yb.mall.common.mq.api.RocketMqConsumerListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* @Description:
* 用来生成DefaultMqPushConsumer
* Rocket 消费模式有两种，PUSH和PULL，即DefaultMQPushConsumer类和DefaultMQPullConsumer类，然而
* DefaultMQPushConsumer内部也是通过PULL模式进行获取
* @author cw
* @date 2019/1/10 15:54
*/
public class MqPushConsumerFactory implements InitializingBean, ApplicationContextAware {


    private String nameSrvAddr;

    private SimpleListenerFactory listenerFactory;

    private ApplicationContext applicationContext;

    private Map<String, DefaultMQPushConsumer> pushConsumerMap;

    private List<DefaultMQPushConsumer> pushConsumers;

    private int consumeThreadMin;

    private int consumeThreadMax;


    MqPushConsumerFactory(String nameSrvAddr) {
        this.nameSrvAddr = nameSrvAddr;
    }

    public Map<String, DefaultMQPushConsumer> getPushConsumerMap() {
        return pushConsumerMap;
    }


    /**
     * 创建消费者
     * @param rocketMqConsumerListener
     * @return
     */
    private DefaultMQPushConsumer createDefaultMQPushConsumer(RocketMqConsumerListener rocketMqConsumerListener) {
        ConsumerConfig config = rocketMqConsumerListener.getConsumerConfig();
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);
        defaultMQPushConsumer.setConsumerGroup(config.getConsumerGroup());
        Map<String, Class<?>> tags = config.getTags();
        StringBuilder tagBuilder = new StringBuilder();
        List<String> tmpTags = new ArrayList<>(tags.keySet());
        for (int i = 0; i < tmpTags.size(); i++) {
            if (tmpTags.contains("*") && tmpTags.size() > 1) {
                throw new IllegalArgumentException("订阅的tag不合法");
            }
            tagBuilder.append(tmpTags.get(i));
            if (tmpTags.size() > i + 1) {
                tagBuilder.append("||");
            }
        }
        try {
            defaultMQPushConsumer.subscribe(config.getTopic(), tagBuilder.toString());
            defaultMQPushConsumer.subscribe(config.getTopic(), "*");
        } catch (MQClientException e) {
            throw new IllegalArgumentException("订阅语法错误", e);
        }
        defaultMQPushConsumer.setMessageModel(config.getMessageModel());
        if (config.getConsumeThreadMax() == 0) {
            defaultMQPushConsumer.setConsumeThreadMax(this.consumeThreadMax);
        } else {
            defaultMQPushConsumer.setConsumeThreadMax(config.getConsumeThreadMax());
        }
        if (config.getConsumeThreadMin() == 0) {
            defaultMQPushConsumer.setConsumeThreadMin(this.consumeThreadMin);
        } else {
            defaultMQPushConsumer.setConsumeThreadMin(config.getConsumeThreadMin());
        }
        return defaultMQPushConsumer;
    }

    public List<DefaultMQPushConsumer> getAllMQPushConsumer() {
        return pushConsumers;
    }


    public SimpleListenerFactory getListenerFactory() {
        return listenerFactory;
    }


    /**
     * spring初始化beans后执行
     */
    @Override
    public void afterPropertiesSet() {
        pushConsumers = new ArrayList<>();
        pushConsumerMap = new HashMap<>(16);
        if (listenerFactory == null) {
            listenerFactory = new SimpleListenerFactory();
            listenerFactory.setApplicationContext(this.applicationContext);
            listenerFactory.afterPropertiesSet();
        }
        listenerFactory.getAllListeners().forEach((topic, consumerListener) -> {
            DefaultMQPushConsumer pushConsumer = createDefaultMQPushConsumer(consumerListener);
            pushConsumers.add(pushConsumer);
            pushConsumerMap.put(topic, pushConsumer);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }


    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }
}
