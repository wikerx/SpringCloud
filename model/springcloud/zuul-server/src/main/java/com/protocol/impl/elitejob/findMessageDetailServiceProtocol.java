package com.protocol.impl.elitejob;

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
 * @Date: 2018/8/22
 * @Time:10:22
 * @Description:
 */
@Service("findMessageDetailServiceProtocol")
public class findMessageDetailServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(findMessageDetailServiceProtocol.class);
    private final static String interfaceDesc = "消息详情";



    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            String fromId = String.valueOf(inputMap.get("fromId"));
            if (VerifyUtils.isEmpty(code, outMap, fromId,"fromId",logger,interfaceDesc))
                return outMap;
            String toId = String.valueOf(inputMap.get("toId"));
            if (VerifyUtils.isEmpty(code, outMap, toId,"toId",logger,interfaceDesc))
                return outMap;
//            String jobId = String.valueOf(inputMap.get("jobId"));
//            if (VerifyUtils.isEmpty(code, outMap, toId,"jobId",logger,interfaceDesc))
//                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}", interfaceDesc, outMap.get("msg"));
        }

        return outMap;
    }
}
