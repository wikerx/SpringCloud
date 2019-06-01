package com.github.zuihou.msgscenter.api;

import java.util.List;


import com.github.zuihou.base.Result;
import com.github.zuihou.msgs.dto.MsgsMobileDTO;
import com.github.zuihou.msgscenter.api.hystrix.MsgsApiHystrix;
import com.github.zuihou.msgscenter.dto.MsgsCenterDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageResultDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/26
 */
@FeignClient(name = "${zuihou.feign.msgs-server:zuihou-msgs-server}", fallback = MsgsApiHystrix.class)
public interface MsgsApi {
    @RequestMapping(value = "/msgsCenterInfo/last", method = RequestMethod.GET)
    Result<MsgsCenterDTO> getLastMsgsAndCount(@RequestParam(value = "userId") Long userId);

    @RequestMapping(value = "/msgsCenterInfo/list", method = RequestMethod.GET)
    Result<List<MsgsCenterInfoPageResultDTO>> getMsgsByBizId(@RequestParam(value = "bizId", required = false) String bizId,
                                                             @RequestParam(value = "bizType") String bizType);

    @RequestMapping(value = "/msgsMobile/last", method = RequestMethod.GET)
    Result<MsgsMobileDTO> getLastMobileAndCount(@RequestParam(value = "userId") Long userId);
}
