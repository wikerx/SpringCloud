
package com.yb.mall.common.api.config;

import lombok.Data;

/*
* @Description: AsyncTask配置
* @author cw
* @date 2019/1/7 14:59
*/
@Data
public class AsyncTaskProperties {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "yb-task-executor-";
}
