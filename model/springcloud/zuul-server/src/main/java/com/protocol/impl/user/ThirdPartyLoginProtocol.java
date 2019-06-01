package com.protocol.impl.user;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 第三方登录
 */
@Service("thirdPartyLoginProtocol")
public class ThirdPartyLoginProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(com.protocol.impl.user.ThirdPartyLoginProtocol.class);
    private final static String interfaceDesc = "第三方登录";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String userType = String.valueOf(inputMap.get("userType"));
            if (VerifyUtils.isEmpty(code, outMap, userType, "userType", logger, interfaceDesc))
                return outMap;

            String openid = String.valueOf(inputMap.get("openid"));
            if (VerifyUtils.isEmpty(code, outMap, openid, "openid", logger, interfaceDesc))
                return outMap;

            String username = String.valueOf(inputMap.get("username"));
            if (VerifyUtils.isEmpty(code, outMap, username, "username", logger, interfaceDesc))
                return outMap;

            String headerPic = String.valueOf(inputMap.get("headerPic"));
            if (VerifyUtils.isEmpty(code, outMap, headerPic, "headerPic", logger, interfaceDesc))
                return outMap;

            String sex = String.valueOf(inputMap.get("sex"));
            if (VerifyUtils.isEmpty(code, outMap, sex, "sex", logger, interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, openid, logger, interfaceDesc))
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

