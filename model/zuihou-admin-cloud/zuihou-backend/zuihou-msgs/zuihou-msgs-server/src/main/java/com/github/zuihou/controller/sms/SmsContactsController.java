package com.github.zuihou.controller.sms;

import java.util.List;

import com.github.zuihou.base.Result;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.sms.entity.SmsContacts;
import com.github.zuihou.sms.example.SmsContactsExample;
import com.github.zuihou.sms.service.SmsContactsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/smsContacts")
@Api(value = "常用联系人", description = "常用联系人")
public class SmsContactsController {
    @Autowired
    private SmsContactsService smsContactsService;


    @ApiOperation(value = "查询", notes = "查询所有")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<SmsContacts>> find() {
        Long userId = BaseContextHandler.getUserId();
        SmsContactsExample example= new SmsContactsExample();
        example.createCriteria().andCreateUserEqualTo(userId);
        example.setOrderByClause(" used_num desc ");
        example.setLimitValue("0, 20");
        return Result.success(smsContactsService.find(example));
    }
}