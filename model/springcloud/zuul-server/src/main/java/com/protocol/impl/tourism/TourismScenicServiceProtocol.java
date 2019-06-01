package com.protocol.impl.tourism;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @Description:    java类作用描述 景点地址
* @Author:         www
* @CreateDate:     2018/8/17 10:01
*/
@Service("tourismScenicServiceProtocol")
public class TourismScenicServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(TourismScenicServiceProtocol.class);
    private final static String interfaceDesc = "景点地址";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口景点地址处理结果：{}", interfaceDesc, outMap.get("msg"));
        }

        return outMap;
    }


}
