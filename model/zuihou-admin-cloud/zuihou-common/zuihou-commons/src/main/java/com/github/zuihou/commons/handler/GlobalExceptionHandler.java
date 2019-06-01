package com.github.zuihou.commons.handler;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.github.zuihou.base.Result;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.exception.BizException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @author zuihou
 * @createTime 2017-12-13 17:04
 */
@ControllerAdvice(value = {
        "com.github.zuihou.auth",
        "com.github.zuihou.gateway",
        "com.github.zuihou.admin.impl",
        "com.github.zuihou.open.impl",
})
@ResponseBody  //返回结果为json
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public Result baseExceptionHandler(BizException ex) {
        log.error("BizException:", ex);
        return new Result(ex.getCode(), null, ex.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("BizException:", ex);
        String message = ex.getMessage();
        if (message != null && !"".equals(message)) {
            if (message.contains("Could not read document:")) {
                String msg = "无法正确的解析json类型的参数：" +
                        message.substring(message.indexOf("Could not read document:") +
                                "Could not read document:".length(), message.indexOf(" at "));
                return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg);
            }
        }
        return Result.result(ExceptionCode.PARAM_EX.getCode(), "", ExceptionCode.PARAM_EX.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public Result BindException(BindException ex) {
        log.error("BizException:", ex);
        BindException eee = (BindException) ex;
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = eee.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数对象[").append(oe.getObjectName()).append("]的字段[")
                        .append(oe.getField()).append("]的值[").append(oe.getRejectedValue()).append("]与实际类型不匹配.")

        );
        return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg.toString());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("BizException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("参数[").append(eee.getName()).append("]的值[")
                .append(eee.getValue()).append("]与实际类型[").append(eee.getRequiredType().getName()).append("]不匹配");
        return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result IllegalStateException(IllegalStateException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.ILLEGALA_RGUMENT_EX.getCode(), null, ExceptionCode.ILLEGALA_RGUMENT_EX.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result MissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("BizException:", ex);
        MissingServletRequestParameterException e = ((MissingServletRequestParameterException) ex);
        StringBuilder msg = new StringBuilder();
        msg.append("缺少必须的[").append(e.getParameterType()).append("] 类型的参数[").append(e.getParameterName()).append("]");
        return Result.result(ExceptionCode.ILLEGALA_RGUMENT_EX.getCode(), null, msg.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result NullPointerException(NullPointerException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.NULL_POINT_EX.getCode(), null, ExceptionCode.NULL_POINT_EX.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result IllegalArgumentException(IllegalArgumentException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.ILLEGALA_RGUMENT_EX.getCode(), null, ExceptionCode.ILLEGALA_RGUMENT_EX.getMsg());
    }

    @ExceptionHandler(SQLException.class)
    public Result SQLException(SQLException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.SQL_EX.getCode(), null, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result DataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.SQL_EX.getCode(), null, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error("BizException:", ex);
        HttpMediaTypeNotSupportedException e = (HttpMediaTypeNotSupportedException) ex;
        MediaType contentType = e.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("请求类型(Content-Type)[").append(contentType.toString()).append("] 与实际接口的请求类型不匹配");
            return Result.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), null, msg.toString());
        }
        return Result.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), null, "无效的Content-Type类型");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public Result MissingServletRequestPartException(MissingServletRequestPartException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
    }

    @ExceptionHandler(ServletException.class)
    public Result ServletException(ServletException ex) {
        log.error("BizException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return Result.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
        }
        return Result.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result MultipartException(MultipartException ex) {
        log.error("BizException:", ex);
        return Result.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> otherExceptionHandler(Exception ex) {
        log.error("Exception:", ex);
        return Result.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ExceptionCode.SYSTEM_BUSY.getMsg());
    }
}