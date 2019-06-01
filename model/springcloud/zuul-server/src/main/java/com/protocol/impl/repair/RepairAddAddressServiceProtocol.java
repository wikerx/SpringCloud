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
 * 添加维修地址
 */
@Service("repairAddAddressServiceProtocol")
public class RepairAddAddressServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "添加维修地址";

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

            String username = String.valueOf(inputMap.get("username"));
            if (VerifyUtils.isEmpty(code, outMap, username, "username", logger, interfaceDesc))
                return outMap;

            String telephone = String.valueOf(inputMap.get("telephone"));
            if (VerifyUtils.isEmpty(code, outMap, telephone, "telephone", logger, interfaceDesc))
                return outMap;

            String mapAddress = String.valueOf(inputMap.get("mapAddress"));
            if (VerifyUtils.isEmpty(code, outMap, mapAddress, "mapAddress", logger, interfaceDesc))
                return outMap;

            String detailAddress = String.valueOf(inputMap.get("detailAddress"));
            if (VerifyUtils.isEmpty(code, outMap, detailAddress, "detailAddress", logger, interfaceDesc))
                return outMap;

            String longitude = String.valueOf(inputMap.get("longitude"));
            if (VerifyUtils.isEmpty(code, outMap, longitude, "longitude", logger, interfaceDesc))
                return outMap;

            String latitude = String.valueOf(inputMap.get("latitude"));
            if (VerifyUtils.isEmpty(code, outMap, latitude, "latitude", logger, interfaceDesc))
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
