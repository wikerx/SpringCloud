package com.yb.mall.common.api.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;


public class AliossUtil {

	private static AliossUtil INSTANCE = new AliossUtil();
	private OSSClient ossClient;
	public String bucketName = "jujiake";
	public String accessKeyId = "LTAIPNqsuh2WkvNL";
	public String accessKeySecret = "pi4wX2zAH0gQRWlhKWqTaVU7aBpEmI";
	public String endpoint = "https://oss.myjujiake.com";
	public static final String ossServiceDomain = "oss.myjujiake.com";
	private AliossUtil(){
		ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
	}

	public static AliossUtil getInstance(){
		return INSTANCE;
	}


	/**
	 *
	 * @param uploadDir
	 *            上传的目录a/b/c/ 为空上传到根目录下
	 * @param key
	 *            上传文件名建议uuid+文件类型.png,.jpg等
	 * @param inputStream
	 *            上传文件流
	 * @param length
	 *            上传文件字节大小 必须
	 * @return 返回阿里云路径
	 * @throws Exception
	 */
	public String putObject(String uploadDir, String key, InputStream inputStream, long length) throws Exception {
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(length);
		if (StringUtils.isNotEmpty(uploadDir)) {
			key = uploadDir + key;
		}
		ossClient.putObject(bucketName, key, inputStream, meta);
		return "https://" + ossServiceDomain + "/" + key;
	}


}
