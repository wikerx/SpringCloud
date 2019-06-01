package com.yb.mall.common.api.core.annotation;


import com.yb.mall.common.api.base.enums.LogTypeEnum;

import java.lang.annotation.*;


/*
* @Description: 操作日志
* @author cw
* @date 2019/1/7 15:00
*/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
	/**
	 * 日志类型
	 *
	 * @return the log type enum
	 */
	LogTypeEnum logType() default LogTypeEnum.OPERATION_LOG;

	/**
	 * 是否保存请求的参数
	 *
	 * @return the boolean
	 */
	boolean isSaveRequestData() default false;

	/**
	 * 是否保存响应的结果
	 *
	 * @return the boolean
	 */
	boolean isSaveResponseData() default false;
}
