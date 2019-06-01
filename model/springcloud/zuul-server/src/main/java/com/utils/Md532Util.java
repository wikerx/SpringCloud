package com.utils;

import com.alibaba.fastjson.JSON;
import com.constant.EnumError;
import com.model.InterfaceCodeVO;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

@Component
public class Md532Util {
    private static final String UTF_8 = "UTF-8";
    protected static final Logger logger = LoggerFactory.getLogger(Md532Util.class);

    protected static final String securityKey = "9761132f19a1398dfd6c7eda8ced1de4";
    protected static String signType = "MD5";

    private static Environment env;

    @Autowired
    public void setEnvironment(Environment environment){
        Md532Util.env = environment;
    }

    public static final String MD5_32bit(String readyEncryptStr) throws Exception {
        if (readyEncryptStr != null) {
            MessageDigest md = MessageDigest.getInstance(signType);
            md.update(readyEncryptStr.getBytes(UTF_8));
            byte[] b = md.digest();
            StringBuilder su = new StringBuilder();
            for (int offset = 0, bLen = b.length; offset < bLen; offset++) {
                String haxHex = Integer.toHexString(b[offset] & 0xFF);
                if (haxHex.length() < 2) {
                    su.append("0");
                }
                su.append(haxHex);
            }
            return su.toString();
        } else {
            return null;
        }
    }

    public static String getSign(Map<String, Object> map) {
        SortedMap packageParams = new TreeMap<>(map);
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry =it.next();
            String k = entry.getKey();
            String v =  entry.getValue();
            if (!TextUtils.isEmpty(v) && !"sign".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String encodeString = sb.substring(0,sb.length() -1).concat(securityKey);
        logger.info("验证原始串(sign)" + encodeString);
        try {
            encodeString = URLEncoder.encode(encodeString, "UTF-8");
            logger.info("验证原始串(encode):{}",encodeString);
            String md5Params = MD5_32bit(encodeString);
            return md5Params;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static HashMap<String, Object> getSignFromInputMap(Map inputMap)
    {
        HashMap returnMap = new HashMap();
        /*//校验一级参数是否必传
        HashMap verifyMap = verifyGlobalParam(inputMap);
        returnMap.put("code", verifyMap.get("code"));
        returnMap.put("desc", verifyMap.get("desc"));
        if (EnumError.SUCCESS_CODE.getCode()!=(int)returnMap.get("code")) {
            return returnMap;
        }*/
        HashMap protocolMap = new HashMap();
        Set set = inputMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)iterator.next();
            Object value = entry.getValue();
            String key = (String)entry.getKey();
            if (((value instanceof String)) && (!"sign".equals(key)) ) {
                protocolMap.put(key, String.valueOf(value));
            }
        }
        String sign = getSign(protocolMap);
        returnMap.put("sign", sign);
        return returnMap;
    }


    public static HashMap<String, Integer> verifyGlobalParam(Map inputMap)
    {
        HashMap returnMap = new HashMap();

        String reqTime = String.valueOf(inputMap.get("reqTime"));
        if (StringUtils.isEmpty(reqTime))
        {
            returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
            returnMap.put("desc", "reqTime" + EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            logger.info("reqTime {}",EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            return returnMap;
        }

        String version = String.valueOf(inputMap.get("version"));
        if (StringUtils.isEmpty(version))
        {
            returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
            returnMap.put("desc", "version"+EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            logger.info("version {}",EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            return returnMap;
        }

        String terminalType = String.valueOf(inputMap.get("terminalType"));
        if (StringUtils.isEmpty(terminalType)){
            returnMap.put("code", EnumError.ERROR_CODE_PARAM_NULL.getCode());
            returnMap.put("desc", "terminalType"+EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            logger.info("terminalType {}",EnumError.ERROR_CODE_PARAM_NULL.getDesc());
            return returnMap;
        }
        returnMap.put("code", EnumError.SUCCESS_CODE.getCode());
        returnMap.put("desc", EnumError.SUCCESS_CODE.getDesc());
        return returnMap;
    }

}

