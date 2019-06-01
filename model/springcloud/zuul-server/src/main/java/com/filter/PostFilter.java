package com.filter;

import com.constant.MessageConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Component
public class PostFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PostFilter.class);


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = getCurrentContext();
            boolean isSuccess = context.get("isSuccess") == null ? true : (boolean) context.get("isSuccess");
            if (!isSuccess) {
                String result = (String) context.get("result");
                logger.info("result:{}", result);
                context.setResponseBody(result);
            } else {
                InputStream stream = context.getResponseDataStream();
                String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                if (!StringUtils.isEmpty(body) && body.contains("服务不可用")) {
                    //发邮件通知,发送短信
                    send(body);
                }
                logger.info("============================================");
                logger.info("响应结果: {}", body);
                logger.info("============================================");
                /*JSONObject jsonObject = (JSONObject)JSON.parse(body);;
                int code = (int)jsonObject.get("code");
                if(code == EnumError.SUCCESS_CODE.getCode()){
                    JSONObject dataObject = (JSONObject)jsonObject.get("data");
                    if(null != dataObject){
                        String dataStr = JSON.toJSONString(dataObject);
                        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                        //设置自定义的响应头Token-Param
                        context.addZuulResponseHeader("Token-Param",String.valueOf(milliSecond));
                        logger.info("dataStr: {},milliSecond: {}",dataStr,milliSecond);
                        //对data进行AES 对称加密，秘钥为时间戳
                        String encrypt = AESUtil.encrypt(dataStr, String.valueOf(milliSecond));
                        String encodeToString = Base64.getEncoder().encodeToString(encrypt.getBytes());
                        logger.info("encrypt: {},base64Encode: {}",encrypt,encodeToString);
                        jsonObject.put("data",encodeToString);
                    }
                }
                logger.info("对响应结果进行加密: {}",JSON.toJSONString(jsonObject));*/
                context.setResponseBody(body);
            }
        } catch (Exception e) {
            rethrowRuntimeException(e);
        }
        return null;
    }

    public void send(String notice) {
        logger.warn("紧急通知:{}", notice);
        this.rabbitMqTemplate.convertAndSend(MessageConstant.EMAIL_QUEUE,notice);
    }

    @Autowired
    private RabbitTemplate rabbitMqTemplate;
}
