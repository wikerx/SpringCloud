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
 * @Time:10:40
 * @Description:
 */
@Service("findNewsDetailServiceProtocol")
public class findNewsDetailServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(findNewsDetailServiceProtocol.class);
    private final static String interfaceDesc = "消息详情";



    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            String id = String.valueOf(inputMap.get("id"));
            if (VerifyUtils.isEmpty(code, outMap, id,"id",logger,interfaceDesc))
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
