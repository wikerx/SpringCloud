package com.constant;

/**
 * 存放rediskey
 * 
 * @author Administrator
 * 
 */
public class RedisKeyConstant {
	/**
	 * 正则表达式.
	 */
	public static final String REGEX = "\\{[a-zA-Z0-9]*\\}";
	
	public static final String user_send_sms = "user:send:sms:{telphone}";
	public static final String user_token = "user:token:{token}";


	public static final String USERID_QUESTIONID_UUID = "user:{userId}:questionId:{questionId}:uuid:{}";

	/**
	 * @param key
	 * @param objs
	 * @return 根据key和参数生成的字符串
	 */
	public static String getKey(String key,Object... objs) {
		for (Object obj : objs) {
			key = key.replaceFirst(REGEX, String.valueOf(obj));
		}
		return key;
	}

	public static void main(String[] args) {
		String userToken = getKey(user_token,"abcdefg");
		System.out.println(userToken);
		//id,telephone,
	}
	
}
