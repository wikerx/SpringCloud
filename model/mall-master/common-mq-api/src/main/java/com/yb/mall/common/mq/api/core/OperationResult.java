package com.yb.mall.common.mq.api.core;

import lombok.Data;


/*
* @Description: Response Result
* @author cw
* @date 2019/1/10 15:52
*/
@Data
public class OperationResult<T> {

    private Boolean success;

    private String message;

    private T data;

    public static OperationResult result(Boolean success,String message){
        OperationResult result = new OperationResult();
        result.setMessage(message);
        result.setSuccess(success);
        return result;
    }


}
