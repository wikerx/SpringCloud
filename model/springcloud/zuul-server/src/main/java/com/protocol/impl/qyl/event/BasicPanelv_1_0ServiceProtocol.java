package com.protocol.impl.qyl.event;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("basicPanelv_1_0ServiceProtocol")
public class BasicPanelv_1_0ServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(BasicPanelv_1_0ServiceProtocol.class);
    private final static String interfaceDesc = "基本盘1.0 版本";

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
