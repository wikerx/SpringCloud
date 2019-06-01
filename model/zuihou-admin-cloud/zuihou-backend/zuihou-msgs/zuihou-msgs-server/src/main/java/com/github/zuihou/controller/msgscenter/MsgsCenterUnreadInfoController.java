package com.github.zuihou.controller.msgscenter;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageReqDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgscenter.service.MsgsCenterUnreadInfoService;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msgsCenterUnreadInfo")
@Api(value = "消息中心未读表", description = "消息中心未读表 该表是#msgs_center_info的子集")
public class MsgsCenterUnreadInfoController {
    @Autowired
    private MsgsCenterUnreadInfoService msgsCenterUnreadInfoService;

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<MsgsCenterInfoPageResultDTO>> page(@RequestBody OpenApiReq<MsgsCenterInfoPageReqDTO> openApiReq) {
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(msgsCenterUnreadInfoService.find(openApiReq.getData())));
    }

}