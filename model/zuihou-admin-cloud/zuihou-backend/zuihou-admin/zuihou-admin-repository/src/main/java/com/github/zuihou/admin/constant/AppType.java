package com.github.zuihou.admin.constant;

/**
 * @author zuihou
 * @createTime 2017-12-15 15:30
 */
public enum AppType {
    APP("应用"),
    SERVER("服务"),
    ;
    private String describe;

    AppType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

}
