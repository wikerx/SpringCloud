package com.protocol.impl.qyl.news;

import com.protocol.ProtocolUtilService;
import com.utils.ErrorLogUtil;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("hitCommentServiceProtocol")
public class HitCommentServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(HitCommentServiceProtocol.class);
    private final static String interfaceDesc = "点赞评论";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId, "userId", logger, interfaceDesc))
                return outMap;

            String opid = String.valueOf(inputMap.get("opid"));
            if (VerifyUtils.isEmpty(code, outMap, opid, "opid", logger, interfaceDesc))
                return outMap;

            String commentId = String.valueOf(inputMap.get("commentId"));
            if (VerifyUtils.isEmpty(code, outMap, commentId, "commentId", logger, interfaceDesc))
                return outMap;

            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, null, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            ErrorLogUtil.catchException(logger, interfaceDesc, outMap, e);
        }
        return outMap;
    }
}
