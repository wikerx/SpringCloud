package com.yb.mall.common.feign.api.feign.search;

import org.springframework.cloud.netflix.feign.FeignClient;

/*
* @Description: 搜索
* @author cw
* @date 2019/1/17 15:03
*/
@FeignClient(name = "property-search-service",fallback = FeignSearchServiceClientHystrix.class)
public interface FeignSearchServiceClient {


}
