package com.protocol.impl.saveSuggest;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cnk
 * @Date: 2018/7/3
 * @Time:14:14
 * @Description:意见反馈接口
 */
@Service("saveSuggestServiceProtocol")
public class SaveSuggestServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SaveSuggestServiceProtocol.class);
    private final static String interfaceDesc = "意见反馈接口";
    /**
     * @description:
     * @param code 访问接口编码
     * @param inputMap  请求参数
     * @return java.util.Map
     * @throws
     * @author cnk
     * @date 2018/7/3
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"用户Id",logger,interfaceDesc))
                return outMap;
            String contact = String.valueOf(inputMap.get("contact"));
            if (VerifyUtils.isEmpty(code, outMap, contact,"用户联系方式",logger,interfaceDesc))
                return outMap;
            String suggest = String.valueOf(inputMap.get("suggest"));
            if (VerifyUtils.isEmpty(code, outMap, suggest,"用户意见",logger,interfaceDesc))
                return outMap;
            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
                return outMap;
            //根据用户id获取token，并校验token是否相等
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,userId,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }


        return outMap;
    }
}
