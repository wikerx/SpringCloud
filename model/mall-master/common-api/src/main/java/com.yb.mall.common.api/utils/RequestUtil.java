
package com.yb.mall.common.api.utils;


import com.yb.mall.common.api.base.constant.GlobalConstant;
import com.yb.mall.common.api.base.dto.LoginAuthDto;
import com.yb.mall.common.api.base.enums.ErrorCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

	/**
	 * Gets request.
	 *
	 * @return the request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获得用户远程地址
	 *
	 * @param request the request
	 *
	 * @return the string
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader(GlobalConstant.X_REAL_IP);
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.X_FORWARDED_FOR);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.PROXY_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.WL_PROXY_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HTTP_CLIENT_IP);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(GlobalConstant.HTTP_X_FORWARDED_FOR);
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		if (StringUtils.isEmpty(ipAddress) || GlobalConstant.UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (GlobalConstant.LOCALHOST_IP.equals(ipAddress) || GlobalConstant.LOCALHOST_IP_16.equals(ipAddress)) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("获取IP地址, 出现异常={}", e.getMessage(), e);
				}
				assert inet != null;
				ipAddress = inet.getHostAddress();
			}
			log.info("获取IP地址 ipAddress={}", ipAddress);
		}
		// 对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > GlobalConstant.MAX_IP_LENGTH) {
			if (ipAddress.indexOf(GlobalConstant.Symbol.COMMA) > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(GlobalConstant.Symbol.COMMA));
			}
		}
		return ipAddress;
	}

	/**
	 * Gets login user.
	 *
	 * @return the login user
	 */
	public static LoginAuthDto getLoginUser() {
		LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMapUtil.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
		if (PublicUtil.isEmpty(loginAuthDto)) {
			throw new BusinessException(ErrorCodeEnum.UAC10011039);
		}
		return loginAuthDto;

	}

	/**
	 * Gets auth header.
	 *
	 * @param request the request
	 *
	 * @return the auth header
	 */
	public static String getAuthHeader(HttpServletRequest request) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (org.apache.commons.lang.StringUtils.isEmpty(authHeader)) {
			throw new BusinessException(ErrorCodeEnum.UAC10011040);
		}
		return authHeader;
	}

}
