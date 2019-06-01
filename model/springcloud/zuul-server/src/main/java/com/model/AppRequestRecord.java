package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppRequestRecord {
    private String appId; //app唯一标识
    private String appName; //app名称
    private String ip;
    private String deviceCode;
    private String currOperation; //当前操作
    private String operationDesc; //操作描述
    private String currView; //当前操作的视图
    private String viewDesc; //视图描述
    private String operationTime; //当次实际操作时间
    private String jsonInfo; //其他数据
}
