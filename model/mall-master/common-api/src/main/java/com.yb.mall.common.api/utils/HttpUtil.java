package com.yb.mall.common.api.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: kyo
 * @Description:
 * @Date: create in 2018-07-18 16:49
 * @Modified:
 */
public class HttpUtil {
    public static String ipAddress(HttpServletRequest request) throws UnknownHostException{
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getRemoteAddr();
        if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress))
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        if (StringUtils.isNotEmpty(ipAddress) && ipAddress.length() > 15 && ipAddress.contains(","))
            ipAddress = ipAddress.split(",")[0];
        return ipAddress;
    }
}
