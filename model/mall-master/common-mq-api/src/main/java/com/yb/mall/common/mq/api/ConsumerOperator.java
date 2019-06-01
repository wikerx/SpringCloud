package com.yb.mall.common.mq.api;


import com.yb.mall.common.mq.api.core.OperationResult;

/*
* @Description: 消费者容器操作接口
* @author cw
* @date 2019/1/10 15:53
*/
public interface ConsumerOperator {


    /**
     * 根据topic暂停某个消费者的消费
     * @param topic 消费者的topic
     * @return 操作结果
     */
    OperationResult suspendConsumer(String topic);

    /**
     * 根据topic恢复某个消费者的消费
     * @param topic 消费者topic
     * @return 操作结果
     */
    OperationResult resumeConsumer(String topic);
}
