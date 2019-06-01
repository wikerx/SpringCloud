package com.yb.mall.common.mq.api.core;

/*
* @Description: 配置
* @author cw
* @date 2019/1/10 15:59
*/
public class MqConfig {
    private Class<?> messageClass;
    private boolean orderlyMessage;

    public Class<?> getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(Class<?> messageClass) {
        this.messageClass = messageClass;
    }

    public boolean isOrderlyMessage() {
        return orderlyMessage;
    }

    public void setOrderlyMessage(boolean orderlyMessage) {
        this.orderlyMessage = orderlyMessage;
    }


}
