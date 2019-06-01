package com.protocol.impl.repair;

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
 * 添加维修信息
 */
@Service("repairAddRepairServiceProtocol")
public class RepairAddRepairServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "添加维修信息";

    /**
     * @param code
     * @param inputMap
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId, "userId", logger, interfaceDesc))
                return outMap;

            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token, "token", logger, interfaceDesc))
                return outMap;

            String title = String.valueOf(inputMap.get("title"));
            if (VerifyUtils.isEmpty(code, outMap, title, "title", logger, interfaceDesc))
                return outMap;

            String serverAddress = String.valueOf(inputMap.get("serverAddress"));
            if (VerifyUtils.isEmpty(code, outMap, serverAddress, "serverAddress", logger, interfaceDesc))
                return outMap;

            String repairPrice = String.valueOf(inputMap.get("repairPrice"));
            if (VerifyUtils.isEmpty(code, outMap, repairPrice, "repairPrice", logger, interfaceDesc))
                return outMap;

            String validTimeStr = String.valueOf(inputMap.get("validTimeStr"));
            if (VerifyUtils.isEmpty(code, outMap, validTimeStr, "validTimeStr", logger, interfaceDesc))
                return outMap;

            String description = String.valueOf(inputMap.get("description"));
            if (VerifyUtils.isEmpty(code, outMap, description, "description", logger, interfaceDesc))
                return outMap;

            String repairTypeIdStr = String.valueOf(inputMap.get("repairTypeIdStr"));
            if (VerifyUtils.isEmpty(code, outMap, repairTypeIdStr, "repairTypeIdStr", logger, interfaceDesc))
                return outMap;

            //根据用户id获取token，并校验token是否相等
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId, logger, interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
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
