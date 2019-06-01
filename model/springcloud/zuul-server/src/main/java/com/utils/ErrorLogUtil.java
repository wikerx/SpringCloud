package com.utils;

import com.constant.EnumError;
import org.slf4j.Logger;

import java.util.HashMap;

public class ErrorLogUtil {
        public static void catchException(Logger logger,String interfaceDesc, HashMap<String, Object> outMap, Exception e) {
            logger.error(e.getMessage(),e);
            outMap.put("code", EnumError.ERROR_CODE.getCode());
            outMap.put("msg", EnumError.ERROR_CODE.getDesc());
            logger.info("{} 接口业务逻辑处理结果：{}",interfaceDesc,outMap.get("msg"));
        }
}