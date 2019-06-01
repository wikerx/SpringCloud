package com.protocol.impl.qyl.news;

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
 * @Date: 2018/7/25
 * @Time:17:32
 * @Description:
 */
@Service("channelDetailServiceProtocol")
public class channelDetailServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(channelDetailServiceProtocol.class);
    private final static String interfaceDesc = "圈子详情";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String channelId = String.valueOf(inputMap.get("channelId"));
            if (VerifyUtils.isEmpty(code, outMap, channelId,"channelId",logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
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
