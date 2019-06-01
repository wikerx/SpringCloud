package com.github.zuihou.controller.sms;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.example.SmsProviderExample;
import com.github.zuihou.sms.service.SmsProviderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.commons.exception.core.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertNotEmpty;
import static com.github.zuihou.utils.BizAssert.assertNotNull;


@RestController
@Slf4j
@RequestMapping("/smsProvider")
@Api(value = "短信供应商", description = "短信供应商")
public class SmsProviderController {
    @Autowired
    private SmsProviderService smsProviderService;

    @ApiOperation(value = "查询", notes = "根据id查询")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<SmsProvider> get(@PathVariable Long id) {
        return Result.success(smsProviderService.getById(id));
    }


    @ApiOperation(value = "查询所有的渠道和对应的模板", notes = "查询所有的渠道和对应的模板 (该接口适用于新建发送或者再次编辑时的下拉框)")
    @ApiResponses({})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result<List<SmsProvider>> findAll() {
        Long userId = BaseContextHandler.getUserId();
        return Result.success(smsProviderService.findAll(userId));
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<SmsProvider>> page(@RequestBody OpenApiReq<SmsProvider> openApiReq) {
        SmsProvider data = openApiReq.getData();
        SmsProviderExample example = new SmsProviderExample();
        SmsProviderExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(data.getType()).andNameLike(SmsProviderExample.fullLike(data.getName()));

        //根据自身业务逻辑写入你的条件。 修改后，请删除该注释
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(smsProviderService.find(example)));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<SmsProvider> save(@RequestBody SmsProvider smsProvider) {
        //1，验证
        valid(smsProvider);
        assertNotEmpty(BASE_VALID_PARAM.build("供应商编码不能为空"), smsProvider.getCode()); //不能修改

        smsProvider.setCreateUserName(BaseContextHandler.getName());

        smsProviderService.saveSelective(smsProvider);
        return Result.success(smsProvider);
    }

    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<SmsProvider> update(@RequestBody SmsProvider smsProvider) {
        //1，验证
        assertNotNull(BASE_VALID_PARAM.build("供应商不能为空"), smsProvider.getId());
        valid(smsProvider);
        smsProvider.setCode(null);
        smsProviderService.updateByIdSelective(smsProvider);
        return Result.success(smsProvider);
    }

    public void valid(SmsProvider smsProvider) {
        assertNotEmpty(BASE_VALID_PARAM.build("供应商类型不能为空"), smsProvider.getType());
        assertNotEmpty(BASE_VALID_PARAM.build("供应商APP_ID不能为空"), smsProvider.getAppId());
        assertNotEmpty(BASE_VALID_PARAM.build("供应商APP_SECRET不能为空"), smsProvider.getAppSecret());
        assertNotEmpty(BASE_VALID_PARAM.build("供应商名称不能为空"), smsProvider.getName());
    }

    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        smsProviderService.deleteById(id);
        return Result.success(true);
    }
}