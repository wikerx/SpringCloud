package com.yb.mall.common.mq.api.core.consumer;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.*;

/*
* @Description: 订阅组
* @author cw
* @date 2019/1/10 16:07
*/
@Data
public class SubscriptionGroup {

    private String topic;

    private List<String> tagList;

    private Object target;

    private Map<String, Method> tagMethods;

    SubscriptionGroup(String topic) {
        this.tagList = new ArrayList<>();
        this.tagMethods = new HashMap<>();
        this.topic = topic;
    }

    public void putTagToGroup(String tag, Method method) {
        if (tagList.contains(tag)) {
            throw new IllegalArgumentException("重复的消费者");
        }
        tagList.add(tag);
        tagMethods.put(tag, method);
    }

    public Method getMethod(String tag) {
        return tagMethods.get(tag);
    }

    public Collection<Method> getAllMethods() {
        return tagMethods.values();
    }

}
