package com.github.zuihou.controller.sms;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.example.SmsSendStatusExample;
import com.github.zuihou.sms.service.SmsSendStatusService;

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
@RequestMapping("/smsSendStatus")
@Api(value = "短信发送状态表", description = "短信发送状态表")
public class SmsSendStatusController {
    @Autowired
    private SmsSendStatusService smsSendStatusService;


    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<SmsSendStatus>> page(@RequestBody OpenApiReq<SmsSendStatus> openApiReq) {
        SmsSendStatus data = openApiReq.getData();
        SmsSendStatusExample example = new SmsSendStatusExample();
        SmsSendStatusExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(data.getTaskId()).andSendStatusEqualTo(data.getSendStatus());
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(smsSendStatusService.find(example)));
    }

}