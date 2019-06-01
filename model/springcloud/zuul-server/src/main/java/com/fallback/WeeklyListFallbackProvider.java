package com.fallback;

import com.alibaba.fastjson.JSON;
import com.constant.EnumError;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 为zuul添加回退
 * @author jzz
 *
 */
@Component
public class WeeklyListFallbackProvider implements FallbackProvider {

	static Map<String,Object> resultMap = new HashMap<>();
	static {
		resultMap.put("code",EnumError.SERVICE_UNAVAILABLE.getCode());
		resultMap.put("msg","周周列表".concat(EnumError.SERVICE_UNAVAILABLE.getDesc()));
	}

	//表明是为哪个微服务提供回退
	@Override
	public String getRoute() {
		return "weeklylist-server";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//可获得造成回退的原因
	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
			return this.fallbackResponse();
		}
	}

	private ClientHttpResponse response(final HttpStatus status){
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application","json",Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}

			//响应体
			@Override
			public InputStream getBody() throws IOException {
				/********<<<<<<<一定要包含"服务不可用">>>>>>>>*****/
				return new ByteArrayInputStream(JSON.toJSONString(resultMap).getBytes(Charset.forName("UTF-8")));
			}

			//状态文本
			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			//fallback时的状态码
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			//数字类型的状态码
			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public void close() {

			}
		};
	}
}
