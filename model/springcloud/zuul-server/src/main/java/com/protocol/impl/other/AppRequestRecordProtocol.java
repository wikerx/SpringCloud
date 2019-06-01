package com.protocol.impl.other;

import com.alibaba.fastjson.JSON;
import com.constant.EnumError;
import com.constant.MessageConstant;
import com.model.AppRequestRecord;
import com.protocol.ProtocolUtilService;
import com.utils.IPv4Util;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取客户端用户操作轨迹
 */
@Service("appRequestRecordProtocol")
public class AppRequestRecordProtocol implements ProtocolUtilService{
    protected final Logger logger = LoggerFactory.getLogger(GetTimestampServiceProtocol.class);
    private final static String interfaceDesc = "获取客户端用户操作轨迹";
    @Autowired
    private RabbitTemplate rabbitMqTemplate;

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String record = String.valueOf(inputMap.get("record"));
            if (VerifyUtils.isEmpty(code, outMap, record,"record",logger,interfaceDesc))
                return outMap;

            send(record);
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
                return outMap;

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }
        return outMap;
    }

    public void send(String notice) {
        logger.warn("发送请求数据到魔曦内部系统:{}", notice);
        this.rabbitMqTemplate.convertAndSend(MessageConstant.MOXI_SYSTEM_QUEUE,notice);
    }
}
