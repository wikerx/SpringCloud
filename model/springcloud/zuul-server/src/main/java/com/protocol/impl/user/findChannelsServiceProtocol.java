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
 * @Auther: HYF
 * @Date: 2018/7/2
 * @Time:14:00
 * @Description:咨询分类
 */
@Service("findChannelsServiceProtocol")
public class findChannelsServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(findChannelsServiceProtocol.class);
    private final static String interfaceDesc = "咨询分类";
    
    /**
     * @description:
     * @param code  访问接口的编码
     * @param inputMap  请求参数
     * @return java.util.Map
     * @throws
     * @author hyf
     * @date 2018/7/2 
     * @time 14:02 
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
//            String userId = String.valueOf(inputMap.get("userId"));
//            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
//                return outMap;
//
//            //根据用户id获取token，并校验token是否为空
//            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
//                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
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
