package com.yb.mall.common.mq.api.core;


import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.yb.mall.common.mq.api.core.producer.MessageProxy;


/*
* @Description: 生产
* @author cw
* @date 2019/1/10 16:31
*/
public interface MqProducer<M> {

    void send(MessageProxy<M> messageProxy) throws MQClientException, InterruptedException, RemotingException;


    void start() throws MQClientException;

    void shutdown();

}
