package com.constant;

public enum EnumError {
    SUCCESS_CODE(0,"成功"),
    ERROR_CODE(1,"失败"),
    SYSTEM_EXCEPITON(1001,"系统异常"),
    ERROR_CODE_TOKEN(1002,"token过期或丢失"),
    ERROR_CODE_PARAM_NULL(1007,"参数为空"),
    ERROR_CODE_FILE_TOBIG(1008,"单个文件过大"),
    MESSAGE_SIGN_NOT_MATCH(1015,"签名不一致"),
    OUT_OF_SYNC_TIME(1019,"时间不同步"),
    REQUEST_TIME_TO_LONG(1020,"请求时间过长"),
    SERVICE_UNAVAILABLE(1021,"服务不可用,请稍后再试")
    ;

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private EnumError(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

}