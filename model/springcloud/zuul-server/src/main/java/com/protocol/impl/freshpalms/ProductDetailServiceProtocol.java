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
 * 商品详情
 */
@Service("productDetailServiceProtocol")
public class ProductDetailServiceProtocol implements ProtocolUtilService {
    protected final Logger logger = LoggerFactory.getLogger(ProductDetailServiceProtocol.class);
    private final static String interfaceDesc = "商品详情";

    /**
     * @param code     访问的接口编码
     * @param inputMap 请求参数
     * @return
     */
    @Override
    public Map service(String code, Map inputMap) {
        HashMap<String, Object> outMap = new HashMap<>();
        try {

            //店铺ID
            String storeId = String.valueOf(inputMap.get("storeId"));
            if (VerifyUtils.isEmpty(code, outMap, storeId, "storeId", logger, interfaceDesc))
                return outMap;

            //商品ID
            String productId = String.valueOf(inputMap.get("productId"));
            if (VerifyUtils.isEmpty(code, outMap, productId, "productId", logger, interfaceDesc))
                return outMap;

            //校验version,reqtime,sign是否为空，校验sign是否正确
            if (VerifyUtils.checkVersionAndReqTimeAndSign(code, inputMap, outMap, storeId, logger, interfaceDesc))
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
