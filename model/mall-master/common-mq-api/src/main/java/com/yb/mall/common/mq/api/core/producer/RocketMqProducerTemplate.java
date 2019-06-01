package com.yb.mall.common.mq.api.core.producer;


import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.yb.mall.common.mq.api.core.MqProducer;
import com.yb.mall.common.mq.api.exception.ContatinerInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 生产模板
 * @param <M>
 */
@Component
public class RocketMqProducerTemplate<M> implements MqProducer<M> {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqProducerTemplate.class);

    @Value("${rocketmq.name-srv-addr}")
    private String namesrvAddr;

    private DefaultMQProducer defaultMQProducer;

    private ProducerConfig producerConfig = new ProducerConfig();

    private AtomicBoolean started = new AtomicBoolean(false);


    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    @Override
    public void start() throws MQClientException {
        if (started.get()) {
            throw new ContatinerInitException("this templeate is already init");
        }
        if (this.defaultMQProducer == null) {
            this.defaultMQProducer = new DefaultMQProducer();
        }
        this.defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
        this.defaultMQProducer.setProducerGroup(this.producerConfig.getProducerGroup());
        this.defaultMQProducer.setSendMsgTimeout(this.producerConfig.getTimeOut());
        this.defaultMQProducer.start();
        this.started.set(true);
    }

    @Override
    public void shutdown() {
        if (started.get()) {
            this.defaultMQProducer.shutdown();
            started.set(false);
        }
    }


    /**
     * 发送消息  使用案例https://gitee.com/jollyfly/rocketmq-spring-boot-starter
     * @param messageProxy
     * @throws MQClientException
     * @throws InterruptedException
     * @throws RemotingException
     */
    @Override
    public void send(MessageProxy messageProxy) throws MQClientException, InterruptedException, RemotingException {
        SendCallback sendCallback = messageProxy.getSendCallback() == null ? new DefaultSendCallback() : messageProxy
                .getSendCallback();
        if (messageProxy.getMessage() == null) {
            throw new NullPointerException("消息不能为空");
        }
        if (this.producerConfig.isOrderlyMessage()) {
            MessageQueueSelector selector = messageProxy.getMessageQueueSelector();
            if (selector == null) {
                throw new NullPointerException("顺序消息必须配置MessageQueueSelector");
            }
            this.defaultMQProducer.send(messageProxy.getMessage(), selector, messageProxy.getSelectorArg(),
                    sendCallback);
        } else {
            this.defaultMQProducer.setVipChannelEnabled(false);//关闭VIP通道
            this.defaultMQProducer.send(messageProxy.getMessage(), sendCallback);
        }
    }

    public ProducerConfig getProducerConfig() {
        return this.producerConfig;
    }

    public void setProducerConfig(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
    }


    private static class DefaultSendCallback implements SendCallback {

        @Override
        public void onSuccess(SendResult sendResult) {
            logger.info("Producer Message Success !");
        }

        @Override
        public void onException(Throwable e) {

        }
    }
}
