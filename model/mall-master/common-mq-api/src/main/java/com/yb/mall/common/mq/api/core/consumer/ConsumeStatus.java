package com.yb.mall.common.mq.api.core.consumer;

/*
* @Description: 消费状态的枚举
* @author cw
* @date 2019/1/10 16:03
*/
public enum ConsumeStatus {
    /**
     * 消费成功
     */
    SUCCESS,
    /**
     * 需要重试
     */
    RETRY
}
