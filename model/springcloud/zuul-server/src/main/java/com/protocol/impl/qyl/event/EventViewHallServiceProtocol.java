package com.protocol.impl.qyl.event;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("eventViewHallServiceProtocol")
public class EventViewHallServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(EventViewHallServiceProtocol.class);
    private final static String interfaceDesc = "观点大厅";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            //操作id
            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid,"opid",logger,interfaceDesc))
                return outMap;

            //判断时间签名
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            ErrorLogUtil.catchException(logger,interfaceDesc,outMap,e);
        }
        return outMap;
    }
}
