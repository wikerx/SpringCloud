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
 * @Time:14:14
 * @Description:咨询列表
 */
@Service("findNewsByChanelServiceProtocol")
public class findNewsByChanelServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(findNewsByChanelServiceProtocol.class);
    private final static String interfaceDesc = "咨询列表";
    /**
     * @description:
     * @param code 访问接口编码
     * @param inputMap  请求参数
     * @return java.util.Map
     * @throws
     * @author hyf
     * @date 2018/7/2 
     * @time 14:14 
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String channelId = String.valueOf(inputMap.get("channelId"));
            if (VerifyUtils.isEmpty(code, outMap, channelId,"channelId",logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,channelId,logger,interfaceDesc))
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
