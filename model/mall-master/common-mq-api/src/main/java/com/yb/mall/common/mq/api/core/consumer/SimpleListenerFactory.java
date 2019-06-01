package com.yb.mall.common.mq.api.core.consumer;



import com.yb.mall.common.mq.api.RocketMqConsumerListener;
import com.yb.mall.common.mq.api.annotation.RocketListeners;
import com.yb.mall.common.mq.api.annotation.RocketMQListener;
import com.yb.mall.common.mq.api.exception.MethodNotSupportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* @Description: 自定义分装监听工厂 （重点 获取所有监听器）
* @author cw
* @date 2019/1/10 15:55
*/
public class SimpleListenerFactory implements InitializingBean, ApplicationContextAware {


    private static final Logger logger = LoggerFactory.getLogger(SimpleListenerFactory.class);

    private Map<String, RocketMqConsumerListener> allListeners;

    private MethodResolver resolver;

    private ApplicationContext context;

    SimpleListenerFactory() {
        this.resolver = new MethodResolver();
    }

    private RocketMqConsumerListener createRocketMqConsumerListener(SubscriptionGroup subscriptionGroup) {
        return new RocketMqListenerMethodAdapter(subscriptionGroup);
    }

    public Map<String, RocketMqConsumerListener> getAllListeners() {
        return allListeners;
    }

    /**
     * 获取每一个topic 对应的消费监听
     */
    @Override
    public void afterPropertiesSet() {
        allListeners = new HashMap<>();
        Map<String, SubscriptionGroup> subscriptionGroups = this.resolver.getSubscriptionGroups();
        subscriptionGroups.forEach((topic, subscriptionGroup) ->
                allListeners.put(topic, createRocketMqConsumerListener(subscriptionGroup)));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.resolver.setApplicationContext(applicationContext);
    }


    /**
     * 方法解析器
     */
    private class MethodResolver implements ApplicationContextAware {

        private ApplicationContext context;

        private Map<String, SubscriptionGroup> subscriptionGroups = new HashMap<>();

        /**
         * 是否初始化订阅
         */
        private boolean initSubscription = false;


        /**
         * 获取订阅组
         * @return
         */
        Map<String, SubscriptionGroup> getSubscriptionGroups() {
            if (!initSubscription) {
                resolveListenerMethod();
            }
            return subscriptionGroups;
        }

        /**
         * 解析监听器方法
         */
        void resolveListenerMethod() {
            //获取到所有拥有特定注解的Beans集合，然后遍历所有bean实现业务场景。此注解为“RocketListeners”
            context.getBeansWithAnnotation(RocketListeners.class).forEach((beanName, obj) -> {
                Map<Method, RocketMQListener> annotatedMethods = MethodIntrospector.selectMethods(obj.getClass(),
                        (MethodIntrospector.MetadataLookup<RocketMQListener>) method -> AnnotatedElementUtils
                                .findMergedAnnotation(method, RocketMQListener.class));
                //根据标志了RocketListeners注解，将其方法放入订阅组中
                initSubscriptionGroup(annotatedMethods, obj);
            });
            this.initSubscription = true;
        }

        private void initSubscriptionGroup(Map<Method, RocketMQListener> annotatedMethod, Object target) {
            if (!CollectionUtils.isEmpty(annotatedMethod)) {
                annotatedMethod.forEach((method, listener) -> {
                    validateMethod(method);
                    RocketListeners rocketListeners = method.getDeclaringClass().getAnnotation(RocketListeners.class);
                    String topic = rocketListeners.topic();
                    String tag = listener.tag();
                    if (subscriptionGroups.containsKey(topic)) {
                        subscriptionGroups.get(topic).putTagToGroup(tag, method);
                    } else {
                        SubscriptionGroup subscriptionGroup = new SubscriptionGroup(topic);
                        subscriptionGroup.putTagToGroup(tag, method);
                        subscriptionGroup.setTarget(target);
                        subscriptionGroups.put(topic, subscriptionGroup);
                    }

                });
            }

        }

        /**
         * 验证方法
         * @param method
         */
        private void validateMethod(Method method) {
            if (method.getParameterCount() > 2) {
                throw new MethodNotSupportException("method: " + method + " 参数列表不被支持");
            }
            boolean typeSupport = Arrays.stream(method.getParameterTypes()).allMatch(parmType -> parmType.equals(method
                    .getAnnotation
                            (RocketMQListener.class).messageClass()) || parmType.equals(MessageContext.class));
            if (!typeSupport) {
                throw new MethodNotSupportException("方法参数中含有不被支持的类型");
            }
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.context = applicationContext;
        }
    }

}
