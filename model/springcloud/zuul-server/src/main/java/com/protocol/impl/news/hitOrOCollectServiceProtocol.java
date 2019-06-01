package com.protocol.impl.news;

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
 * @Date: 2018/7/4
 * @Time:15:51
 * @Description:咨询点赞
 */
@Service("hitOrOCollectServiceProtocol")
public class hitOrOCollectServiceProtocol implements ProtocolUtilService {

    protected final Logger logger = LoggerFactory.getLogger(hitOrOCollectServiceProtocol.class);
    private final static String interfaceDesc = "咨询点赞或收藏";
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;
            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
                return outMap;
            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId,logger,interfaceDesc))
                return outMap;
            String newsId = String.valueOf(inputMap.get("newsId"));
            if (VerifyUtils.isEmpty(code, outMap, newsId,"newsId",logger,interfaceDesc))
                return outMap;
            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid,"opid",logger,interfaceDesc))
                return outMap;
            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口点赞处理结果：{}", interfaceDesc, outMap.get("msg"));
        }

        return outMap;
    }
}
