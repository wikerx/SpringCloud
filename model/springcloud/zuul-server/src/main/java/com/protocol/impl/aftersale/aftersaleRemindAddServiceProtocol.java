package com.protocol.impl.aftersale;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.protocol.impl.user.SignInServiceProtocol;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * aftersale添加提醒事项
 */
@Service("aftersaleRemindAddServiceProtocol")
public class aftersaleRemindAddServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "aftersale添加提醒事项";

    /**
     * @param code
     * @param inputMap
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String title = String.valueOf(inputMap.get("title"));
            if (VerifyUtils.isEmpty(code, outMap, title,"title",logger,interfaceDesc))
                return outMap;

            String times = String.valueOf(inputMap.get("times"));
            if (VerifyUtils.isEmpty(code, outMap, times,"times",logger,interfaceDesc))
                return outMap;

            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, title, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}", interfaceDesc, outMap.get("msg"));
        }
        return outMap;
    }


}
