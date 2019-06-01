package com.protocol.impl.user;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 忘记密码(重置密码)
 */
@Service("forgetPasswordServiceProtocol")
public class ForgetPasswordServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(ForgetPasswordServiceProtocol.class);
    private final static String interfaceDesc = "忘记密码(重置密码)";
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
            String smsCode = String.valueOf(inputMap.get("code"));
            if (VerifyUtils.isEmpty(code, outMap, smsCode,"smsCode",logger,interfaceDesc))
                return outMap;

            String password = String.valueOf(inputMap.get("password"));
            if (VerifyUtils.isEmpty(code, outMap,password, "password",logger,interfaceDesc))
                return outMap;

            String telephone = String.valueOf(inputMap.get("telephone"));
            if (VerifyUtils.isEmpty(code, outMap,telephone, "telephone",logger,interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, telephone,logger,interfaceDesc))
                return outMap;
            //签名正确，放过请求
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }

        return outMap;
    }


}
