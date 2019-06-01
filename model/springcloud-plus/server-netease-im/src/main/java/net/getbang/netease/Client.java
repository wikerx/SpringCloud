package net.getbang.netease;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class Client {

	private static final String SERVER_URL = "https://api.netease.im/nimserver/user/create.action";//校验验证码的请求路径URL
    private static final String APP_KEY = "d58d5c0714677f90823de39011729452";//网易云信分配的账号
    private static final String APP_SECRET = "20c93a2e0e2a";//网易云信分配的密钥
    private static final String NONCE = "123456";//随机数

	public static void main(String[] args) throws Exception {
		
		
		
	craeteUser();	
		
		
		
		
	}
	
	public static String craeteUser() throws Exception {
		
		
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 
		 HttpPost post = new HttpPost(SERVER_URL);
		 
		 String curTime = String.valueOf((new Date().getTime() / 1000L));
	     String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
		 
	     
	     //设置请求的header
	        post.addHeader("AppKey", APP_KEY);
	        post.addHeader("Nonce", NONCE);
	        post.addHeader("CurTime", curTime);
	        post.addHeader("CheckSum", checkSum);
	        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	        
	        //设置请求参数
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("accid", "1234"));
	        nameValuePairs.add(new BasicNameValuePair("name", "張三3"));

	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
	        
	        //执行请求
	        HttpResponse response = httpclient.execute(post);
	        String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
	        
	        
	        // 打印执行结果
	      //  System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	      //判断是否发送成功，发送成功返回true
	        String code = JSON.parseObject(responseEntity).getString("code");
	        
	        System.out.println(JSON.parseObject(responseEntity));
	        
	        if (code.equals("200")) {
	           return "success";
	        }
	        return "error";
	}
	
	
}
