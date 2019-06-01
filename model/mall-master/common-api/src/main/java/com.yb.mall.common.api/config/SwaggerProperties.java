
package com.yb.mall.common.api.config;

import lombok.Data;

/*
* @Description: Swagger配置
* @author cw
* @date 2019/1/7 14:58
*/
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "update";

	private String contactUrl = "http://www.updatecg.xin/";

	private String contactEmail = "chenwu_cg@126.com";
}
