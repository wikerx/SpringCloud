package com.yb.mall.common.api.rest;

import com.yb.mall.common.api.base.enums.YesOrNoEnum;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
* @Description: 接口返回结果
* @author cw
* @date 2019/1/17 15:45
*/
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestResult {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseEntity<RestResult> success(String msg, Object data) {
        if (data == null){
            data = new Object();
        }
        return new ResponseEntity<>(new RestResult(YesOrNoEnum.NO.getCode(), msg, data), HttpStatus.OK);
    }

    public static ResponseEntity<RestResult> success(Object data) {
        return success(YesOrNoEnum.NO.getRestMsg(), data);
    }

    public static ResponseEntity<RestResult> success() {
        return success(new Object());
    }

    public static ResponseEntity<RestResult> error(String msg, Object data) {
        return new ResponseEntity<>(new RestResult(YesOrNoEnum.YES.getCode(), msg, data), HttpStatus.OK);
    }

    public static ResponseEntity<RestResult> error(String code,String msg) {
        return new ResponseEntity<>(new RestResult(Integer.valueOf(code), msg, new Object()), HttpStatus.OK);
    }

    public static ResponseEntity<RestResult> error(String msg) {
        return error(msg, new Object());
    }

    public static ResponseEntity<RestResult> error() {
        return error(YesOrNoEnum.YES.getRestMsg(),new Object());
    }

}
