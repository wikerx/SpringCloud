package com.github.zuihou.admin.rest.account.api;

import com.github.zuihou.admin.rest.account.dto.ApplicationsDTO;
import com.github.zuihou.admin.rest.account.api.hystrix.ApplicationsApiHystrix;
import com.github.zuihou.base.Result;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zuihou
 * @createTime 2017-12-08 16:07
 */
@FeignClient(name = "${zuihou.feign-server.admin:zuihou-admin-server}", fallback = ApplicationsApiHystrix.class)
public interface ApplicationsApi {
    /**
     * 根据appid 和 密码 查找应用
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return
     */
    @RequestMapping(value = "/app/{appId}/{appSecret}", method = RequestMethod.GET)
    Result<ApplicationsDTO> getBySecret(@PathVariable(value = "appId") String appId, @PathVariable(value = "appSecret") String appSecret);

}
