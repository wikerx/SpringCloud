package com.protocol.impl.qyl.common;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("instant2ServiceProtocol")
public class InstantServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(InstantServiceProtocol.class);
    private final static String interfaceDesc = "优化即时赛事";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid, "opid", logger, interfaceDesc))
                return outMap;

            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            ErrorLogUtil.catchException(logger, interfaceDesc, outMap, e);
        }
        return outMap;
    }
}
