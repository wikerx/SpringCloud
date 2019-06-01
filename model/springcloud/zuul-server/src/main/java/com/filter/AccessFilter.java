package com.filter;

import com.alibaba.fastjson.JSON;
import com.constant.EnumError;
import com.factory.ProtocolServiceFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.protocol.ProtocolUtilService;
import com.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求过滤
 */
@Component
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    private long requestIntervalTime = 1200; //请求间隔时间,防止同一个请求url多次访问
    @Autowired
    private Environment environment;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 判断是否有accessToken参数，若有则进行路由，若没有就拒绝访问，返回401 Unauthorized错误
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String contentType = request.getContentType();
        logger.info(String.format("send %s method contentType %s request to %s", request.getMethod(),contentType,request.getRequestURL().toString()));
        Random random = new Random();
        String requestIds = String.valueOf(random.nextLong());
        try {
            List<String> ipList = GetLocalIp.getIpAddress("eth0");
            logger.info("本机IP地址：" + ipList);
            if (null != ipList && ipList.size() > 0) {
                requestIds = requestIds.concat("|").concat(ipList.get(0));
            }
        } catch (Exception e) {
            logger.error("获取本机IP出现异常：" + e.getMessage(), e);
            requestIds = String.valueOf(random.nextLong());
        }

        try {
            String ipAddress = GetRemoteIpAddr.getIpAddr(request);

            String url = request.getRequestURL().toString();
            if (StringUtils.isEmpty(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            logger.info("请求方ipAddress: {},url: {}", ipAddress, url);
            Map<String, Object> returnMap = new HashMap<String, Object>();
            Map<String, String[]> parameterMap = new HashMap<>();

            /**** 支持websocket推送 ****/
            String upgradeHeader = request.getHeader("Upgrade");
            if (null == upgradeHeader) {
                upgradeHeader = request.getHeader("upgrade");
            }
            if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)) {
                logger.info("websocket push message");
                context.addZuulRequestHeader("connection", "Upgrade");
            }

            if(!StringUtils.isEmpty(contentType) && contentType.indexOf(MediaType.MULTIPART_FORM_DATA_VALUE) != -1){
                CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
                if(multipartResolver.isMultipart(request)) {
                    //判断request是否有文件上传
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) multipartResolver.resolveMultipart(request);
                    Iterator<String> ite = multiRequest.getFileNames();
                    AtomicInteger ai = new AtomicInteger(0);
                    long fileSumSize = 0;
                    String maxFileSize = environment.getProperty("spring.http.multipart.max-file-size","1048576").replace("Mb",""); //默认1M
                    String maxRequestSize = environment.getProperty("spring.http.multipart.max-request-size","20971520").replace("Mb",""); //默认20M
                    logger.info("限制单个文件上传大小:{} Mb, 单个请求的文件大小:{} Mb",maxFileSize, maxRequestSize);

                    while (ite.hasNext()) {
                        List<MultipartFile> files = multiRequest.getFiles(ite.next());
                        for(MultipartFile file: files){
                            logger.info("文件名:{}, 文件大小:{} bytes",file.getName(), file.getSize());
                            if (file != null) {
                                ai.getAndIncrement();
                            }
                            fileSumSize +=file.getSize();
                        }
                    }

//                    if(ai.get() <= 0){
//                        logger.warn(EnumError.ERROR_CODE_PARAM_NULL.getCode()+",检查上传的文件");
//                        returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
//                        returnMap.put("msg","检查上传的文件");
//                        responseError(context, returnMap);
//                        return null;
//                    }

                    logger.info("fileSumSize:{},file num:{}",fileSumSize,ai.get());
                    parameterMap = multiRequest.getParameterMap();
                }
            }else if(!StringUtils.isEmpty(contentType) && contentType.indexOf(MediaType.APPLICATION_JSON_UTF8_VALUE) != -1){
                String bodyString = getBodyString(request);
                logger.info(bodyString);
            }else {
                //application/x-www-form-urlencoded
                parameterMap = request.getParameterMap();
            }

            logger.info("请求参数：{}", JSON.toJSONString(parameterMap));
            if (parameterMap == null || parameterMap.isEmpty()) {
                logger.warn("非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg",EnumError.ERROR_CODE_PARAM_NULL.getDesc());
                responseError(context, returnMap);
                return null;
            }

            String[] interfaceCodes = parameterMap.get("interfaceCode");
            if (interfaceCodes == null) {
                logger.warn("interfaceCode未传,非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","interfaceCode".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String[] versions = parameterMap.get("version");
            if (versions == null) {
                logger.warn("version,非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","version".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String[] terminalTypes = parameterMap.get("terminalType");
            if (terminalTypes == null) {
                logger.warn("terminalType,非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","terminalType".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String[] reqTimes = parameterMap.get("reqTime");
            if (reqTimes == null) {
                logger.warn("reqTime,非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","reqTime".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String[] signs = parameterMap.get("sign");
            if (signs == null) {
                logger.warn("sign,非法请求");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","sign".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String interfaceCode = parameterMap.get("interfaceCode")[0];
            if (StringUtils.isEmpty(interfaceCode)) {
                logger.warn("interfaceCode 为空");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","interfaceCode".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String version = parameterMap.get("version")[0];
            if (StringUtils.isEmpty(version)) {
                logger.warn("version 为空");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","version".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String terminalType = parameterMap.get("terminalType")[0];
            if (StringUtils.isEmpty(terminalType)) {
                logger.warn("terminalType 为空");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","terminalType".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String reqTime = parameterMap.get("reqTime")[0];
            if (StringUtils.isEmpty(reqTime)) {
                logger.warn("reqTime 为空");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","reqTime".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            String sign = parameterMap.get("sign")[0];
            if (StringUtils.isEmpty(sign)) {
                logger.warn("sign 为空");
                returnMap.put("code",EnumError.ERROR_CODE_PARAM_NULL.getCode());
                returnMap.put("msg","sign".concat(EnumError.ERROR_CODE_PARAM_NULL.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            long requestTime = Long.parseLong(reqTime);
            long end = System.currentTimeMillis();
            logger.info("请求时间: {} <{}>, 当前时间:{} <{}>",requestTime,
                    DateUtil.transferLongToDate(DateUtil.yyyyMMddHHmmssSSS,requestTime),end,
                    DateUtil.transferLongToDate(DateUtil.yyyyMMddHHmmssSSS,end));

            /*if(requestTime > end-30){ //服务器时间和客户端时间误差在30
                //客户端的时间比服务器的时间快
                logger.warn("检查请求的时间,和服务器时间不一致");
                returnMap.put("code",EnumError.OUT_OF_SYNC_TIME.getCode());
                returnMap.put("msg","reqTime".concat(EnumError.OUT_OF_SYNC_TIME.getDesc()));
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(403);
                context.set("isSuccess", false);
                context.set("result",JSON.toJSONString(returnMap));
                context.getResponse().setContentType("application/json;charset=UTF-8");
                return null;
            }*/

            if((end - requestTime)/1000  > requestIntervalTime){
                logger.warn("请求间隔过长,超过设定的时间");
                returnMap.put("code",EnumError.REQUEST_TIME_TO_LONG.getCode());
                returnMap.put("msg","reqTime".concat(EnumError.REQUEST_TIME_TO_LONG.getDesc()));
                responseError(context, returnMap);
                return null;
            }

            /*********************黑名单处理***mq异步入库******************************/
            /******************防止并发处理***************************/

            ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
            ProtocolUtilService xmlService = ProtocolServiceFactory.createService(interfaceCode, applicationContext);
            if (xmlService == null) {
                returnMap.put("code",EnumError.SYSTEM_EXCEPITON.getCode());
                returnMap.put("msg","检查文件interfaceCode是否存在");
                responseError(context, returnMap);
                return null;
            }
            Map<String, String> inputMap = MapUtils.toMap(parameterMap);
            returnMap = xmlService.service(interfaceCode, inputMap);//处理业务
            logger.info("签名返回结果：{}", returnMap);
            if (!returnMap.isEmpty() && (int) returnMap.get("code") == EnumError.SUCCESS_CODE.getCode()) {
                logger.info(">>>>>签名通过>>>>>");
                return null;
            }
            responseError(context, returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理业务出现异常：" + e.getMessage(), e);
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(500);
            return null;
        }

        return null;
    }

    public void responseError(RequestContext context, Map<String, Object> returnMap) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(200);//返回错误码
        context.set("isSuccess", false);
        context.set("result", JSON.toJSONString(returnMap));
        context.getResponse().setContentType("application/json;charset=UTF-8");
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
