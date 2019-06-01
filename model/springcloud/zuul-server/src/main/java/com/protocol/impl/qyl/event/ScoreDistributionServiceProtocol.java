package com.protocol.impl.qyl.event;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("scoreDistributionServiceProtocol")
public class ScoreDistributionServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(ScoreDistributionServiceProtocol.class);
    private final static String interfaceDesc = "进球分布";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
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
