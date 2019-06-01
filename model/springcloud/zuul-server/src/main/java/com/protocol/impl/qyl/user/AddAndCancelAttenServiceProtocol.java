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
 * 关注（取消关注）粉丝接口
 */
@Service("addAndCancelAttenServiceProtocol")
public class AddAndCancelAttenServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(AddAndCancelAttenServiceProtocol.class);
    private final static String interfaceDesc = "关注（取消关注）粉丝接口";
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

            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid,"opid",logger,interfaceDesc))
                return outMap;

            String ownerId = String.valueOf(inputMap.get("ownerId"));
            if (VerifyUtils.isEmpty(code, outMap, ownerId,"ownerId",logger,interfaceDesc))
                return outMap;

            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
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
