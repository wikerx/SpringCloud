package com.protocol.impl.qyl.event;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("postViewv_1_0ServiceProtocol")
public class PostViewv_1_0ServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(PostViewv_1_0ServiceProtocol.class);
    private final static String interfaceDesc = "发布观点1.0";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;

            String matchId = String.valueOf(inputMap.get("matchId"));
            if (VerifyUtils.isEmpty(code, outMap, matchId,"matchId",logger,interfaceDesc))
                return outMap;

            String viewReason = String.valueOf(inputMap.get("viewReason"));
            if (VerifyUtils.isEmpty(code, outMap, viewReason,"viewReason",logger,interfaceDesc))
                return outMap;

            String betScore = String.valueOf(inputMap.get("betScore"));
            if (VerifyUtils.isEmpty(code, outMap, betScore,"betScore",logger,interfaceDesc))
                return outMap;

            String eventView = String.valueOf(inputMap.get("eventView"));
            if (VerifyUtils.isEmpty(code, outMap, eventView,"eventView",logger,interfaceDesc))
                return outMap;

            String consumeScore = String.valueOf(inputMap.get("consumeScore"));
            if (VerifyUtils.isEmpty(code, outMap, consumeScore,"consumeScore",logger,interfaceDesc))
                return outMap;

            String oddsData = String.valueOf(inputMap.get("oddsData"));
            if (VerifyUtils.isEmpty(code, outMap, oddsData,"oddsData",logger,interfaceDesc))
                return outMap;

            String eventNm = String.valueOf(inputMap.get("eventNm"));
            if (VerifyUtils.isEmpty(code, outMap, eventNm,"eventNm",logger,interfaceDesc))
                return outMap;

            String homeNm = String.valueOf(inputMap.get("homeNm"));
            if (VerifyUtils.isEmpty(code, outMap, homeNm,"homeNm",logger,interfaceDesc))
                return outMap;

            String awayNm = String.valueOf(inputMap.get("awayNm"));
            if (VerifyUtils.isEmpty(code, outMap, awayNm,"awayNm",logger,interfaceDesc))
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
