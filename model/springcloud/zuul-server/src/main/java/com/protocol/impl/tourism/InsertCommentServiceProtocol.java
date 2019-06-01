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
* @Description:    java类作用描述 用户评论
* @Author:         www
* @CreateDate:     2018/8/17 10:48
*/
@Service("insertCommentServiceProtocol")
public class InsertCommentServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(InsertCommentServiceProtocol.class);
    private final static String interfaceDesc = "用户评论";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            //commentsContent 判空
            String commentsContent = String.valueOf(inputMap.get("commentsContent"));
            if (VerifyUtils.isEmpty(code, outMap,commentsContent, "commentsContent",logger,interfaceDesc))
                return outMap;


            String newsId = String.valueOf(inputMap.get("newsId"));
            if (VerifyUtils.isEmpty(code, outMap,newsId, "newsId",logger,interfaceDesc))
                return outMap;

            String commentsUid = String.valueOf(inputMap.get("commentsUid"));
            if (VerifyUtils.isEmpty(code, outMap,commentsUid, "commentsUid",logger,interfaceDesc))
                return outMap;


            //根据用户id获取token，并校验token是否为空
            if (VerifyUtils.getTokenByUserId(code, inputMap, outMap, commentsUid,logger,interfaceDesc))
                return outMap;


            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, commentsUid, logger, interfaceDesc))
                return outMap;
            //签名正确，放过请求
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口用户评论处理结果：{}", interfaceDesc, outMap.get("msg"));
        }

        return outMap;
    }


}
