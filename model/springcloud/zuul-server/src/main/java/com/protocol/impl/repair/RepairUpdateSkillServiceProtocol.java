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
 * 修改师傅技能
 */
@Service("repairUpdateSkillServiceProtocol")
public class RepairUpdateSkillServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "修改师傅技能";

    /**
     * @param code
     * @param inputMap
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String id = String.valueOf(inputMap.get("id"));
            if (VerifyUtils.isEmpty(code, outMap, id, "id", logger, interfaceDesc))
                return outMap;
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId, "userId", logger, interfaceDesc))
                return outMap;

            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token, "token", logger, interfaceDesc))
                return outMap;

            String serviceSkills = String.valueOf(inputMap.get("serviceSkills"));
            if (VerifyUtils.isEmpty(code, outMap, serviceSkills, "serviceSkills", logger, interfaceDesc))
                return outMap;

            String serviceZone = String.valueOf(inputMap.get("serviceZone"));
            if (VerifyUtils.isEmpty(code, outMap, serviceZone, "serviceZone", logger, interfaceDesc))
                return outMap;

            String workingTime = String.valueOf(inputMap.get("workingTime"));
            if (VerifyUtils.isEmpty(code, outMap, workingTime, "workingTime", logger, interfaceDesc))
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
