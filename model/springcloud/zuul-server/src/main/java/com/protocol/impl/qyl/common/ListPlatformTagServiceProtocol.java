package com.protocol.impl.qyl.common;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("listPlatformTagServiceProtocol")
public class ListPlatformTagServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(ListPlatformTagServiceProtocol.class);
    private final static String interfaceDesc = "平台tag列表";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String platform = String.valueOf(inputMap.get("platform"));
            if (VerifyUtils.isEmpty(code, outMap, platform, "platform", logger, interfaceDesc))
                return outMap;
            String locationId = String.valueOf(inputMap.get("locationId"));
            if (VerifyUtils.isEmpty(code, outMap, locationId, "locationId", logger, interfaceDesc))
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
