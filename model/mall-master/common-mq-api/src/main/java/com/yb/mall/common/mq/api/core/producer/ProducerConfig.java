package com.yb.mall.common.mq.api.core.producer;


import com.yb.mall.common.mq.api.core.MqConfig;

/*
* @Description: 生产配置
* @author cw
* @date 2019/1/10 16:30
*/
public final class ProducerConfig extends MqConfig {

    private String producerGroup = "ybgroup";

    private int timeOut = 3000;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }


    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
