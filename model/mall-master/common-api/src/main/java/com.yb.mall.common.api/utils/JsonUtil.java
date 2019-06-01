package com.yb.mall.common.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * JsonUtil
 *
 * @author : pzc
 * @create : 2017 - 05 - 26
 */
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 去除默认的时间格式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置时区为中国上海
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 序列化时 NULL 的处理
        OBJECT_MAPPER.setSerializationInclusion(Include.ALWAYS);
        // 反序列化时，属性不存在的兼容处理
        OBJECT_MAPPER.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时，统一的时间格式处理
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号的处理
        OBJECT_MAPPER.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * Generic to String
     *
     * @param src object
     * @return string
     */
    public static <T> String toJson(T src) {
        if (src instanceof String) {
            return (String) src;
        } else {
            try {
                return OBJECT_MAPPER.writeValueAsString(src);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * String to Generic
     *
     * @param json string
     * @param clazz Class
     * @return T
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (StringUtils.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T toObject(Object object, Class<T> clazz) {
        return toObject(JsonUtil.toJson(object), clazz);
    }

    /**
     * String to Map
     *
     * @param json string
     * @return Map
     */
    public static Map toMap(String json) {
        return toObject(json, HashMap.class);
    }

    /**
     * Json to Collection
     *
     * @param json string
     * @param typeReference TypeReference
     * @return Object
     */
    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json to Collection
     *
     * @param object object
     * @param typeReference TypeReference
     * @return Object
     */
    public static <T> T toCollection(Object object, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(toJson(object), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
