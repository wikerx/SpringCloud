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
 * 校验手机号和下发验证码
 * www
 * 2018-07-03
 */
@Service("verifyPhoneAndSendCodeProtocol")
public class VerifyPhoneAndSendCodeProtocol implements ProtocolUtilService{
        protected final Logger logger = LoggerFactory.getLogger(VerifyPhoneAndSendCodeProtocol.class);
        private final static String interfaceDesc = "校验手机号和下发验证码";
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
                String telephone = String.valueOf(inputMap.get("telephone"));
                if (VerifyUtils.isEmpty(code, outMap, telephone,"telephone",logger,interfaceDesc))
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
