package com.protocol.impl.qyl.user;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改性别
 */
@Service("updateSexServiceProtocol")
public class UpdateSexServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(UpdateSexServiceProtocol.class);
    private final static String interfaceDesc = "修改性别";
    /**
     *
     * @param code 访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;

            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
                return outMap;

            //根据用户id获取token，并校验token是否相等
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;

            String sex = String.valueOf(inputMap.get("sex"));
            if (VerifyUtils.isEmpty(code, outMap,sex, "sex",logger,interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }

        return outMap;
    }


}
