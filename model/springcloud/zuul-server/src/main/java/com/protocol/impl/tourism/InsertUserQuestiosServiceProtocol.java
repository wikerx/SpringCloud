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
* @Description:    java类作用描述 用户提问
* @Author:         www
* @CreateDate:     2018/8/17 10:48
*/
@Service("insertUserQuestiosServiceProtocol")
public class InsertUserQuestiosServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(InsertUserQuestiosServiceProtocol.class);
    private final static String interfaceDesc = "用户提问";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            String questionsUid = String.valueOf(inputMap.get("questionsUid"));
            if (VerifyUtils.isEmpty(code, outMap,questionsUid, "questionsUid",logger,interfaceDesc))
                return outMap;

            String questions = String.valueOf(inputMap.get("questions"));
            if (VerifyUtils.isEmpty(code, outMap,questions, "questions",logger,interfaceDesc))
                return outMap;



            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, questionsUid,logger,interfaceDesc))
                return outMap;


            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, questionsUid, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口用户提问处理结果：{}", interfaceDesc, outMap.get("msg"));
        }

        return outMap;
    }


}
