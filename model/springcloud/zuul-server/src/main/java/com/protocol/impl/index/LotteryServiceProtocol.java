package com.protocol.impl.index;

import com.constant.EnumError;
import com.protocol.ProtocolUtilService;
import com.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cnk
 * @Date: 2018/7/3
 * @Time:14:14
 * @Description:首页彩票入口接口
 */
@Service("lotteryServiceProtocol")
public class LotteryServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(LotteryServiceProtocol.class);
    private final static String interfaceDesc = "首页彩票入口接口";
    /**
     * @description:
     * @param code 访问接口编码
     * @param inputMap  请求参数
     * @return java.util.Map
     * @throws
     * @author cnk
     * @date 2018/7/3
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String,Object> outMap = new HashMap<>();
        try {
            String nameEn = String.valueOf(inputMap.get("nameEn"));
            if (VerifyUtils.isEmpty(code, outMap, nameEn,"彩种英文缩写",logger,interfaceDesc))
                return outMap;
//            校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap,nameEn,logger,interfaceDesc))
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
