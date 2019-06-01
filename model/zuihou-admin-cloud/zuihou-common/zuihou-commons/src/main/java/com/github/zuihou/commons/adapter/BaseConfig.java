package com.github.zuihou.commons.adapter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.base.id.SnowflakeIDGenerate;
import com.github.zuihou.commons.converter.String2DateConverter;
import com.github.zuihou.commons.feign.DateFormatRegister;
import com.github.zuihou.commons.handler.GlobalExceptionHandler;
import com.github.zuihou.utils.SpringUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public abstract class BaseConfig {

    /**
     * json 类型参数 序列化问题
     * Long -> string
     * date -> string
     *
     * @param builder
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"))
                .build();
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);//排除空字段  : 不能排除，否则前端不能显示null字段，不友好
        //忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //日期格式
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(outputFormat);
        SimpleModule simpleModule = new SimpleModule();
        /**
         *  将Long,BigInteger序列化的时候,转化为String
         */
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);

        //在进出前后台的时候，设置BigDecimal和字符串之间转换
        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    /**
     * date 类型参数 格式问题
     *
     * @return
     */
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new String2DateConverter();
    }

    /**
     * 在feign调用方配置， 解决入参和出参是 date 类型
     *
     * @return
     */
    @Bean
    public DateFormatRegister dateFormatRegister() {
        return new DateFormatRegister();
    }


    /**
     * id生成 机器码， 单机配置1即可。 集群部署，每个实例自增1即可。
     *
     * @param machineCode
     * @return
     */
    @Bean
    public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
        return new SnowflakeIDGenerate(machineCode);
    }

    @Bean
    public SpringUtil springUtil(ApplicationContext applicationContext) {
        SpringUtil.setApplicationContext(applicationContext);
        return new SpringUtil();
    }

    /**
     * 全局异常处理
     *
     * @return
     */
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
