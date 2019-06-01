package com.yb.mall.common.api.base.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;


/**
 * @Author: kyo
 * @Description: 0, 1
 * @Date: create in 2018\3\16 9:59
 * @Modified:
 */
@Getter
public enum YesOrNoEnum {
    NONE(-1,"",""),

    YES(1, "是", "系统异常"),
    NO(0, "否", "操作成功");

    private Integer code;
    private String value;
    private String restMsg; // rest接口结果

    YesOrNoEnum(Integer code, String value, String restMsg) {
        this.code = code;
        this.value = value;
        this.restMsg = restMsg;
    }

    public static YesOrNoEnum self(Integer code){
        return Arrays.stream(values())
                .filter(yesOrNoEnum ->  Objects.equals(code,yesOrNoEnum.getCode()))
                .findAny()
                .orElse(NONE);
    }

}
