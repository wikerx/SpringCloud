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
 * @Date: 2018/7/26
 * @Time:10:20
 * @Description:
 */
@Service("personalForumListServiceProtocol")
public class personalForumListServiceProtocol  implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(personalForumListServiceProtocol.class);
    private final static String interfaceDesc = "个人（圈子话题和赛事观点）";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            //验证被关注者id
            String ownerId = String.valueOf(inputMap.get("ownerId"));
            if (VerifyUtils.isEmpty(code, outMap, ownerId,"ownerId",logger,interfaceDesc))
                return outMap;
            //验证被关注者id
            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid,"opid",logger,interfaceDesc))
                return outMap;
            //验证token
//            String token = String.valueOf(inputMap.get("token"));
//            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
//                return outMap;
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
