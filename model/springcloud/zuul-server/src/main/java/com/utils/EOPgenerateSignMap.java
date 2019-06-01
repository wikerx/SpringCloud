package com.utils;

import com.alibaba.fastjson.JSON;
import com.constant.EnumError;
import com.model.InterfaceCodeVO;
import com.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取请求Map一级参数，供生成验签串使用
 *
 */
@Component
public class EOPgenerateSignMap {
	
	private static final Logger log = LoggerFactory.getLogger(EOPgenerateSignMap.class);
	private static RedisService redisService;
	private static Environment env;

	@Autowired(required = true)
	public void setRedisService(RedisService redisService){
		EOPgenerateSignMap.redisService = redisService;
	}

	@Autowired
	public void setEnvironment(Environment environment){
		EOPgenerateSignMap.env = environment;
	}

	//获取一级参数Map
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> getFirstLevelParamMap(Map inputMap, String interfaceCode){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, String> signMap = null;
		//一级key值
		//String firstLevelKeys = EOPinterfaceFirstkeyManager.getInstance().getInterfaceFirstKeys(interfaceCode, "");
		String ic = env.getProperty(interfaceCode,"");
		String globalFirstParamKey = env.getProperty("globalFirstParamKey");
		if(null == ic || "".equals(ic)){
			resultMap.put("code", EnumError.ERROR_CODE_PARAM_NULL.getCode());
			resultMap.put("msg", "接口代码为‘" + interfaceCode + "’的一级验签key值没有在 interfacecode 表中进行配置，请检查");
			log.info("接口代码为[{}]的一级验签key值没有在 interfacecode 表中进行配置，请检查",interfaceCode);
			return resultMap;
		}

		InterfaceCodeVO interfaceCodeVO = JSON.parseObject(ic,InterfaceCodeVO.class);
		InterfaceCodeVO globalFirstParamKeyVO = JSON.parseObject(globalFirstParamKey,InterfaceCodeVO.class);
		String firstLevelKeys = globalFirstParamKeyVO.getKeys().concat(",").concat(interfaceCodeVO.getKeys());
		log.info("<<<<待分割的一级验签key值：" + firstLevelKeys);
		if(null == firstLevelKeys || "".equals(firstLevelKeys)){
			resultMap.put("code", EnumError.ERROR_CODE_PARAM_NULL.getCode());
			resultMap.put("msg", "接口代码为‘" + interfaceCode + "’的一级验签key值没有在interfaceFirstKey.xml中进行配置，请检查");
			log.info("接口代码为[{}]的一级验签key值没有在interfaceFirstKey.xml中进行配置，请检查",interfaceCode);
			return resultMap;
		}else{
			String[] keysArray = RegularUtils.splitStringToArray(firstLevelKeys, ",");
			if(null == keysArray || keysArray.length == 0){
				resultMap.put("code", EnumError.ERROR_CODE_PARAM_NULL.getCode());
				resultMap.put("msg", "用逗号分割一级验签key值出现异常");
				log.info("用逗号分割一级验签key值出现异常");
				return resultMap;
			}
			signMap = new HashMap<String, String>();
			for(String key : keysArray){
				signMap.put(key, String.valueOf(inputMap.get(key)));
			}
			resultMap.put("code", EnumError.SUCCESS_CODE.getCode());
			resultMap.put("msg", "接口代码：" + interfaceCode + "，生成一级验签Map成功");
			resultMap.put("signMap", signMap);
			return resultMap;
		}
	}
}
