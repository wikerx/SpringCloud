package com.protocol.impl.qyl.taskwall;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("exchangeTaskServiceProtocol")
public class ExchangeTaskServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(ExchangeTaskServiceProtocol.class);
    private final static String interfaceDesc = "任务墙-兑换积分";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            //userId判空
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;

            //任务id判空
            String taskId = String.valueOf(inputMap.get("taskId"));
            if (VerifyUtils.isEmpty(code, outMap, taskId,"taskId",logger,interfaceDesc))
                return outMap;

            //试玩时间判空
            String durationTime = String.valueOf(inputMap.get("durationTime"));
            if (VerifyUtils.isEmpty(code, outMap, durationTime,"durationTime",logger,interfaceDesc))
                return outMap;

            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;

            //判断版本 ，time,sign
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            ErrorLogUtil.catchException(logger, interfaceDesc, outMap, e);
        }
        return outMap;
    }
}
