package com.protocol.impl.freshpalms;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 卖家版 - 文章评论
 */
@Service("freshNewsCommentServiceProtocol")
public class FreshNewsCommentServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(FreshNewsCommentServiceProtocol.class);
    private final static String interfaceDesc = "卖家版 - 文章评论";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            //文章ID
            String newsId = String.valueOf(inputMap.get("newsId"));
            if (VerifyUtils.isEmpty(code, outMap, newsId, "newsId", logger, interfaceDesc))
                return outMap;

            //评论内容
            String comment = String.valueOf(inputMap.get("comment"));
            if (VerifyUtils.isEmpty(code, outMap, comment, "comment", logger, interfaceDesc))
                return outMap;

            //用户ID
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId, "userId", logger, interfaceDesc))
                return outMap;

            //token
            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token, "token", logger, interfaceDesc))
                return outMap;

            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, userId, logger, interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, userId, logger, interfaceDesc))
                return outMap;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}", interfaceDesc, outMap.get("msg"));
        }
        return outMap;
    }


}
