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
 * @Time:9:49
 * @Description:
 */
@Service("findJobByTabServiceProtocol")
public class findJobByTabServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(findJobByTabServiceProtocol.class);
    private final static String interfaceDesc = "根据导航条职位或公司";



    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            String opId = String.valueOf(inputMap.get("opId"));
            if (VerifyUtils.isEmpty(code, outMap, opId,"导航操作id",logger,interfaceDesc))
                return outMap;

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
