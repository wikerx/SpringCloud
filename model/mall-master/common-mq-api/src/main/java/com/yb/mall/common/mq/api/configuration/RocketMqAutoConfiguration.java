package com.yb.mall.common.mq.api.configuration;


import com.yb.mall.common.mq.api.core.consumer.RocketMessageListenerContainer;
import com.yb.mall.common.mq.api.core.producer.MqProducerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/*
* @Description: 自动化配置类
* @author cw
* @date 2019/1/10 15:45
*/
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqAutoConfiguration {

    @Autowired
    private RocketMqProperties rocketMqProperties;

    /**
     * 消息消费监听器容器类配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(RocketMessageListenerContainer.class)//仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
    public RocketMessageListenerContainer rocketMessageListenerContainer() {
        RocketMessageListenerContainer container = new RocketMessageListenerContainer();
        container.setNameSrvAddr(rocketMqProperties.getNameSrvAddr());
        container.setConsumeThreadMax(rocketMqProperties.getConsumeThreadMax());
        container.setConsumeThreadMin(rocketMqProperties.getConsumeThreadMin());
        return container;
    }

    /**
     * 消息生产容器类配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(MqProducerContainer.class)
    public MqProducerContainer mqProducerContainer() {
        return new MqProducerContainer();
    }

}
