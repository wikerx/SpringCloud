package com.protocol.impl.qyl.event;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("postViewAbilityServiceProtocol")
public class PostViewAbilityServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(PostViewAbilityServiceProtocol.class);
    private final static String interfaceDesc = "可否发布赛事观点";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
//            String userId = String.valueOf(inputMap.get("userId"));
//            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
//                return outMap;

            String matchId = String.valueOf(inputMap.get("matchId"));
            if (VerifyUtils.isEmpty(code, outMap, matchId,"matchId",logger,interfaceDesc))
                return outMap;

            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            ErrorLogUtil.catchException(logger,interfaceDesc,outMap,e);
        }
        return outMap;
    }
}
