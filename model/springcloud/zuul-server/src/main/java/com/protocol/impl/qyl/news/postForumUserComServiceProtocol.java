package com.protocol.impl.qyl.news;

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
 * @Date: 2018/7/26
 * @Time:13:51
 * @Description:
 */
@Service("postForumUserComServiceProtocol")
public class postForumUserComServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(postForumUserComServiceProtocol.class);
    private final static String interfaceDesc = "论坛下的互相评论";

    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            //验证被关注者id
            String userId = String.valueOf(inputMap.get("userId"));
            if (VerifyUtils.isEmpty(code, outMap, userId,"userId",logger,interfaceDesc))
                return outMap;
            //验证新闻id
            String newsId = String.valueOf(inputMap.get("newsId"));
            if (VerifyUtils.isEmpty(code, outMap, newsId,"newsId",logger,interfaceDesc))
                return outMap;
            //验证评论
            String content = String.valueOf(inputMap.get("content"));
            if (VerifyUtils.isEmpty(code, outMap, content,"content",logger,interfaceDesc))
                return outMap;
            //验证被评论id
            String parentId = String.valueOf(inputMap.get("parentId"));
            if (VerifyUtils.isEmpty(code, outMap, parentId,"parentId",logger,interfaceDesc))
                return outMap;
            //验证评论id
            String commentId = String.valueOf(inputMap.get("commentId"));
            if (VerifyUtils.isEmpty(code, outMap, commentId,"commentId",logger,interfaceDesc))
                return outMap;
            //验证token
            String token = String.valueOf(inputMap.get("token"));
            if (VerifyUtils.isEmpty(code, outMap, token,"token",logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,null,logger,interfaceDesc))
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
