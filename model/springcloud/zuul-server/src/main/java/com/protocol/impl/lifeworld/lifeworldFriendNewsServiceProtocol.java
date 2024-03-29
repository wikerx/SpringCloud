package com.protocol.impl.lifeworld;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.protocol.impl.user.SignInServiceProtocol;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 生活圈（发布生活圈）
 */
@Service("lifeworldFriendNewsServiceProtocol")
public class lifeworldFriendNewsServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(SignInServiceProtocol.class);
    private final static String interfaceDesc = "生活圈（发布生活圈）";
    /**
     *
     * @param code 访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String states = String.valueOf(inputMap.get("states"));
            if (VerifyUtils.isEmpty(code, outMap, states,"states",logger,interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, states,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 生活圈（发布生活圈）业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }

        return outMap;
    }


}
