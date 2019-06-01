package com.yb.mall.common.mq.api.exception;

/*
* @Description: 消费异常
* @author cw
* @date 2019/1/10 16:00
*/
public class ConsumeException extends RuntimeException{
    private static final long serialVersionUID = 4093867789628938836L;


    public ConsumeException(String message) {
        super(message);
    }

    public ConsumeException(Throwable cause) {
        super(cause);
    }

    public ConsumeException(String message, Throwable cause) {
        super(message, cause);
    }
}
