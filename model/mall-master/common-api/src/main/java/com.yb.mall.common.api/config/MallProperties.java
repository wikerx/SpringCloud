

package com.yb.mall.common.api.config;


import com.yb.mall.common.api.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
* @Description: 商城配置
* @author cw
* @date 2019/1/7 15:41
*/
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class MallProperties {
	private AliyunProperties aliyun = new AliyunProperties();
	private AsyncTaskProperties task = new AsyncTaskProperties();
	private SwaggerProperties swagger = new SwaggerProperties();
	private JobProperties job = new JobProperties();
	private ZookeeperProperties zk = new ZookeeperProperties();
}
