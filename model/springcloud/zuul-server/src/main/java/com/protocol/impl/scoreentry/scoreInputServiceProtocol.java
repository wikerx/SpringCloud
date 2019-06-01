package com.protocol.impl.scoreentry;

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
 * 积分录入系统首页
 */
@Service("scoreInputServiceProtocol")
public class scoreInputServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "积分录入系统首页";
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

            String typeId = String.valueOf(inputMap.get("typeId"));
            if (VerifyUtils.isEmpty(code, outMap, typeId,"typeId",logger,interfaceDesc))
                return outMap;

            String numbers = String.valueOf(inputMap.get("numbers"));
            if (VerifyUtils.isEmpty(code, outMap, numbers,"numbers",logger,interfaceDesc))
                return outMap;

            String time = String.valueOf(inputMap.get("time"));
            if (VerifyUtils.isEmpty(code, outMap, time,"time",logger,interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 积分录入系统首页接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }

        return outMap;
    }


}
