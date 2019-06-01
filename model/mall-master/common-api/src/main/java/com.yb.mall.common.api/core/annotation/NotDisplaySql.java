

package com.yb.mall.common.api.core.annotation;

import java.lang.annotation.*;

/*
* @Description: 配合 SqlLogInterceptor 对指定方法 禁止打印SQL到控制台
* @author cw
* @date 2019/1/7 15:01
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NotDisplaySql {
}
