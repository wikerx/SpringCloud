package com.github.zuihou.commons.constant;

/**
 * 这里只配置内部系统的APP_ID
 *
 * @author zuihou
 * @date 2018/12/26
 */
public enum SystemApplication {
    AUTHORITY("zcyy", "10000", "10000","权限管理系统", "/cloud-authority"),
    MSGS("zcyy", "10001", "10001","消息管理系统", "/cloud-msgs"),
    ;
    private String type;
    private String appId;
    private String appSecret;
    private String appName;
    private String contextPath;

    SystemApplication(String type, String appId, String appSecret, String appName, String contextPath) {
        this.type = type;
        this.appId = appId;
        this.appSecret = appSecret;
        this.contextPath = contextPath;
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppName() {
        return appName;
    }

    public String getType() {
        return type;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getContextPath() {
        return contextPath;
    }}
