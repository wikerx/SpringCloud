package com.protocol.impl.qyl.ad;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("findAdsServiceProtocol")
public class FindAdsServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(FindAdsServiceProtocol.class);
    private final static String interfaceDesc = "获取广告页";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            ErrorLogUtil.catchException(logger,interfaceDesc,outMap,e);
        }
        return outMap;
    }
}
