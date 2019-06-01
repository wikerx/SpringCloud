package com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.constant.EnumError;
import com.constant.RedisKeyConstant;
import com.service.RedisService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VerifyUtils {
    private static RedisService redisService;
    private static Environment env;

    @Autowired
    public void setEnvironment(Environment environment){
        VerifyUtils.env = environment;
    }

    @Autowired
    public void setRedisService(RedisService redisService){
        VerifyUtils.redisService = redisService;
    }
    /**
     * 校验非空并记录日志
     * @param code 接口名字
     * @param outMap 返回
     * @param parameter 参数
     * @param logger
     * @param interfaceDesc 接口描述
     * @return
     */
    public static boolean isEmpty(String code, HashMap<String, Object> outMap,String parameter, String parameterDesc, Logger logger,String interfaceDesc) {
        if(StringUtils.isEmpty(parameter) || "null".equals(parameter.toLowerCase())){
            outMap.put("code", EnumError.ERROR_CODE_PARAM_NULL.getCode());
            outMap.put("msg",parameterDesc.concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
            logger.info("请求接口:{} 请求参数:{} 错误描述:{}",code,parameterDesc,outMap.get("msg"));
            logger.info("请求接口:{} 接口的响应报文:{}",code,outMap.toString());
            logger.info("请求接口:{} ##########:{}接口结束##########",code,interfaceDesc);
            return true;
        }
        return false;
    }

    public static boolean CheckSign(String code, Map inputMap, HashMap<String, Object> outMap, String telephone, String sign,Logger logger,String interfaceDesc) {
        //校验一级参数是否必传
        HashMap verifyMap = Md532Util.verifyGlobalParam(inputMap);
        outMap.put("code", verifyMap.get("code"));
        outMap.put("msg", verifyMap.get("desc"));
        if (EnumError.SUCCESS_CODE.getCode()!=(int)outMap.get("code")) {
            return true;
        }

        HashMap<String, Object> returnMap = EOPgenerateSignMap.getFirstLevelParamMap(inputMap, code);
        logger.info("[telephone: {}]生成一级验签Map结果：{}",telephone,returnMap);
        if(!String.valueOf(EnumError.SUCCESS_CODE.getCode()).equals(String.valueOf(returnMap.get("code")))){
            outMap.put("code", returnMap.get("code"));
            outMap.put("msg", String.valueOf(returnMap.get("msg")));
            logger.info("请求接口:{}[telephone: {}]<<验证结果：{}",code,telephone,outMap.get("msg"));
            logger.info("请求接口:{}[telephone: {}]的响应报文:{}",code,telephone,outMap.toString());
            logger.info("请求接口:{}[telephone: {}]####################{}接口结束 ####################",code,interfaceDesc);
            return true;
        }
        HashMap<String, String> signMap = (HashMap<String, String>)returnMap.get("signMap");
        HashMap<String, Object> protoMap = Md532Util.getSignFromInputMap(signMap);
        /*Integer code1 = (Integer) protoMap.get("code");
        if(EnumError.SUCCESS_CODE.getCode() != code1.intValue()){
            outMap.put("code", protoMap.get("code"));
            outMap.put("msg", protoMap.get("desc"));
            logger.info("[sign:"+inputMap.get("sign")+"](1)<<验证结果：" + outMap.get("msg"));
            logger.info("[sign:"+inputMap.get("sign")+"]的响应报文:" + outMap.toString());
            logger.info("[sign:"+inputMap.get("sign")+"]####################{}接口结束 ####################");
            return true;
        }*/
        //上包项目不做签名校验
        String module = String.valueOf(inputMap.get("module"))=="null" ? "" : String.valueOf(inputMap.get("module"));
        String project_module = env.getProperty("shangbao.project.module","");
        String interfaceCode = env.getProperty(code,"");
        logger.info("project_module:{}, interfaceCode:{}",project_module, interfaceCode);
        String[] split = project_module.split(",");
        List<String> modules = Arrays.asList(split);
        if(modules.size() > 0 && modules.contains(module)){
            logger.info("上包项目不做签名校验");
            outMap.put("code",EnumError.SUCCESS_CODE.getCode());
            outMap.put("msg",EnumError.SUCCESS_CODE.getDesc());
            return true;
        }

        String serverSign = (String)protoMap.get("sign");
        if(!serverSign.equals(sign)){
            outMap.put("code", EnumError.MESSAGE_SIGN_NOT_MATCH.getCode());
            outMap.put("msg", "请求接口"+code+EnumError.MESSAGE_SIGN_NOT_MATCH.getDesc());
            logger.info("[sign:"+inputMap.get("sign")+"](1)<<验证结果：请求接口" + code + "签名不正确 请求sign=" + sign + "，服务器sign=" + serverSign);
            return true;
        }else {
            outMap.put("code",EnumError.SUCCESS_CODE.getCode());
            outMap.put("msg",EnumError.SUCCESS_CODE.getDesc());
        }
        return false;
    }


    /**
     * 根据用户id获取token，并校验token是否相等
     */
    public static boolean getTokenByUserId(String code, Map inputMap, HashMap<String, Object> outMap, String userId,Logger logger,String interfaceDesc) {
        //说明需要token进行签名
        String module = String.valueOf(inputMap.get("module"));
        module = StringUtils.isEmpty(module) || module.equalsIgnoreCase("null") ? "" : module.concat(":");

        String userTokenKey = RedisKeyConstant.getKey(module.concat(RedisKeyConstant.user_token), userId);
        String userTokenValue = redisService.get(userTokenKey);
        logger.info("module:{}, userTokenKey: {},userTokenValue: {}",module, userTokenKey,userTokenValue);
        if(StringUtils.isEmpty(userTokenValue)){
            outMap.put("code", EnumError.ERROR_CODE_TOKEN.getCode());
            outMap.put("msg",EnumError.ERROR_CODE_TOKEN.getDesc());
            logger.warn("userId: {}",EnumError.ERROR_CODE_TOKEN.getDesc());
            return true;
        }
        JSONObject jsonObject = JSON.parseObject(userTokenValue);
        String tokenServer = "";
        if(jsonObject != null) {
            tokenServer = (String) jsonObject.get("token");
        }
        String tokenClient = String.valueOf(inputMap.get("token")); //客户端传递的token
        if(!tokenClient.equals(tokenServer)){
            logger.warn("userId: {} 请求的token错误,tokenClient: {},tokenServer: {}",userId,tokenClient,tokenServer);
            outMap.put("code", EnumError.ERROR_CODE_TOKEN.getCode());
            outMap.put("msg",EnumError.ERROR_CODE_TOKEN.getDesc());
            return true;
        }
        return false;
    }

    /**
     *校验version,reqtime,sign是否为空，校验sign是否正确
     */
    public static boolean checkVersionAndReqTimeAndSign(String code, Map inputMap, HashMap<String, Object> outMap, String userId,Logger logger,String interfaceDesc) {
        String version = String.valueOf(inputMap.get("version"));
        if (VerifyUtils.isEmpty(code, outMap,version,"version",logger,interfaceDesc))
            return true;

        String reqTime = String.valueOf(inputMap.get("reqTime"));
        if (VerifyUtils.isEmpty(code, outMap,reqTime,"reqTime",logger,interfaceDesc))
            return true;

        String sign = String.valueOf(inputMap.get("sign"));
        if (VerifyUtils.isEmpty(code, outMap,sign, "sign",logger,interfaceDesc))
            return true;

        /***验证签名是否正确***/
        if (VerifyUtils.CheckSign(code, inputMap, outMap, userId, sign,logger,interfaceDesc))
            return true;
        //签名正确，放过请求
        return false;
    }

}
