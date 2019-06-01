package com.yb.mall.common.mq.api.core.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.yb.mall.common.mq.api.ConsumerOperator;
import com.yb.mall.common.mq.api.RocketMqConsumerListener;
import com.yb.mall.common.mq.api.core.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * @Description:
 *  消息监听容器
 *
 * 此处InitializingBean作用简单说明一下：
 *  1、方法较适用于分布式项目数据库
 *  2、spring初始化bean的时候，如果bean实现了InitializingBean接口，会自动调用afterPropertiesSet方法。
 * @author cw
 * @date 2019/1/10 15:50
 */
public class RocketMessageListenerContainer implements InitializingBean, DisposableBean,
        SmartLifecycle, ApplicationContextAware, ConsumerOperator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String nameSrvAddr;

    private int consumeThreadMin;

    private int consumeThreadMax;


    private final Object monitor = new Object();

    private final Object mapMonitor = new Object();

    private volatile boolean running = false;

    private volatile boolean initialized = false;

    private List<DefaultMQPushConsumer> pushConsumers = new CopyOnWriteArrayList<>();

    private Map<String, DefaultMQPushConsumer> pushConsumerMap = new ConcurrentHashMap<>();

    private Map<String, DefaultMQPushConsumer> removedMap = new ConcurrentHashMap<>();

    private Map<String, DefaultMQPushConsumer> runningMap = new ConcurrentHashMap<>();

    private Map<String, Map.Entry<DefaultMQPushConsumer, String>> startErrMap = new ConcurrentHashMap<>();

    private MqPushConsumerFactory consumerFactory;

    private ApplicationContext applicationContext;


    /**
     * 消息监听容器初始化
     */
    @Override
    public void start() {
        if (!isRunning()) {
            running = true;
            synchronized (monitor) {
                registMessageListener();
                startAllListener();
            }
        }
    }

    /**
     * 开启所有监听器
     */
    private void startAllListener() {
        pushConsumerMap.forEach((topic, consumer) -> {
            try {
                consumer.start();
                runningMap.put(topic, consumer);
            } catch (MQClientException e) {
                logger.error(e.getErrorMessage());
                Map.Entry<DefaultMQPushConsumer, String> errEntry =
                        new AbstractMap.SimpleEntry<>(consumer, e.getErrorMessage());
                startErrMap.put(topic, errEntry);
            }
        });
    }

    /**
     * 停止Mq消费
     */
    @Override
    public void stop() {
        if (isRunning()) {
            running = false;
            pushConsumers.forEach(DefaultMQPushConsumer::shutdown);
        }
        if (logger.isDebugEnabled()) {
            logger.info("Stopped RocketMessageListenerContainer");
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * Mq杀死
     */
    @Override
    public void destroy() {
        this.initialized = false;
        stop();
    }

    @Override
    public void afterPropertiesSet() {
        initMqPushConsumerFactory();
        this.initialized = true;

    }

    /**
     * 初始化消息消费工厂
     */
    private void initMqPushConsumerFactory() {
        this.consumerFactory = new MqPushConsumerFactory(this.nameSrvAddr);
        this.consumerFactory.setApplicationContext(applicationContext);
        this.consumerFactory.setConsumeThreadMax(this.consumeThreadMax);
        this.consumerFactory.setConsumeThreadMin(this.consumeThreadMin);
        this.consumerFactory.afterPropertiesSet();

    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    /**
     * 注册消息监听器
     */
    private void registMessageListener() {
        SimpleListenerFactory listenerFactory = consumerFactory.getListenerFactory();
        pushConsumers.addAll(consumerFactory.getAllMQPushConsumer());
        pushConsumerMap.putAll(consumerFactory.getPushConsumerMap());
        Map<String, RocketMqConsumerListener> listenerMap = listenerFactory.getAllListeners();
        pushConsumerMap.forEach((topic, consumer) -> {
            RocketMqConsumerListener listener = listenerMap.get(topic);
            if (listener.getConsumerConfig().isOrderlyMessage()) {
                consumer.registerMessageListener(new MessageListenerOrderlyImpl(listener));
            } else {
                consumer.registerMessageListener(new MessageListenerConcurrentlyImpl(listener));
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 根据topic暂停某个消费者的消费
     * @param topic 消费者的topic
     * @return 操作结果
     */
    @Override
    public OperationResult suspendConsumer(String topic) {
        return analyzeResult(topic, OperatinType.SUSPEND, () -> {
            DefaultMQPushConsumer consumer = runningMap.get(topic);
            consumer.suspend();
            runningMap.remove(topic);
            removedMap.put(topic, consumer);
        });
    }

    /**
     * 根据topic恢复某个消费者的消费
     * @param topic 消费者topic
     * @return 操作结果
     */
    @Override
    public OperationResult resumeConsumer(String topic) {
        return analyzeResult(topic, OperatinType.RESUME, () -> {
            DefaultMQPushConsumer consumer = removedMap.get(topic);
            consumer.resume();
            removedMap.remove(topic);
            runningMap.put(topic, consumer);
        });
    }

    private OperationResult analyzeResult(String topic, OperatinType operatinType, Runnable runnable) {

        if (initialized) {
            FindResult findResult = findInMap(topic);
            OperationResult result = new OperationResult();
            switch (findResult) {
                case NONE:
                    result.setSuccess(false);
                    result.setMessage("未找到对应的消费者");
                    break;
                case ERROR:
                    result.setSuccess(false);
                    result.setMessage("该消费者启动异常");
                    break;
                case RUNNING:
                    if (operatinType.equals(OperatinType.SUSPEND)) {
                        runnable.run();
                        result.setSuccess(true);
                        result.setMessage("暂停成功");
                    } else {
                        result.setMessage("该消费者正在运行中");
                        result.setSuccess(false);
                    }
                    break;
                case SUSPEND:
                    if (operatinType.equals(OperatinType.RESUME)) {
                        synchronized (mapMonitor) {
                            runnable.run();
                            result.setSuccess(true);
                            result.setMessage("运行成功");
                        }
                    } else {
                        result.setSuccess(false);
                        result.setMessage("该消费者正在暂停中");
                    }
                    break;
                default:
                    result.setMessage("未知异常");
                    result.setSuccess(false);
                    break;
            }
            return result;

        }
        return OperationResult.result(false, "容器未初始化");
    }


    private FindResult findInMap(String topic) {
        if (!pushConsumerMap.containsKey(topic)) {
            return FindResult.NONE;
        } else {
            if (startErrMap.containsKey(topic)) {
                return FindResult.START_ERROR;
            }
            if (runningMap.containsKey(topic)) {
                return FindResult.RUNNING;
            }
            if (removedMap.containsKey(topic)) {
                return FindResult.SUSPEND;
            }
        }
        return FindResult.ERROR;
    }

    private enum FindResult {
        //未找到
        NONE,
        //启动异常
        START_ERROR,
        //运行中
        RUNNING,
        //停止
        SUSPEND,
        //其他异常
        ERROR
    }

    private enum OperatinType {
        //恢复
        RESUME,
        //暂停
        SUSPEND
    }

    public String getNameSrvAddr() {
        return nameSrvAddr;
    }

    public void setNameSrvAddr(String nameSrvAddr) {
        this.nameSrvAddr = nameSrvAddr;
    }

    public MqPushConsumerFactory getConsumerFactory() {
        return consumerFactory;
    }

    public void setConsumerFactory(MqPushConsumerFactory consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }
}
