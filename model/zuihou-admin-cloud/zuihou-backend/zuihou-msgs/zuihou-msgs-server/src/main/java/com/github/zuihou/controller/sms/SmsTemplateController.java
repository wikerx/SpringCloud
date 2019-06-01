package com.github.zuihou.controller.sms;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.example.SmsTemplateExample;
import com.github.zuihou.sms.service.SmsProviderService;
import com.github.zuihou.sms.service.SmsTemplateService;

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
@RequestMapping("/smsTemplate")
@Api(value = "短信模板表", description = "短信模板表")
public class SmsTemplateController {
    @Autowired
    private SmsTemplateService smsTemplateService;
    @Autowired
    private SmsProviderService smsProviderService;

    @ApiOperation(value = "查询", notes = "根据id查询")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<SmsTemplate> get(@PathVariable Long id) {
        return Result.success(smsTemplateService.getById(id));
    }

    @ApiOperation(value = "查询", notes = "查询所有")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<SmsTemplate>> find() {
        Long userId = BaseContextHandler.getUserId();
        SmsTemplateExample example = new SmsTemplateExample();
        example.createCriteria().andCreateUserEqualTo(userId);
        return Result.success(smsTemplateService.find(example));
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<SmsTemplate>> page(@RequestBody OpenApiReq<SmsTemplate> openApiReq) {
        SmsTemplate data = openApiReq.getData();

        SmsTemplateExample example = new SmsTemplateExample();
        SmsTemplateExample.Criteria criteria = example.createCriteria();

        criteria.andNameLike(BaseExample.fullLike(data.getName()))
                .andProviderIdEqualTo(data.getProviderId())
                .andTemplateCodeLike(BaseExample.fullLike(data.getTemplateCode()))
                .andCreateTimeLessThanOrEqualToFooter(data.getEndTime())
                .andCreateTimeGreaterThanOrEqualToTop(data.getStartTime());

        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(smsTemplateService.find(example)));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<SmsTemplate> save(@RequestBody SmsTemplate smsTemplate) {
        //1,验证
        valid(smsTemplate);

        SmsProvider smsProvider = smsProviderService.getById(smsTemplate.getProviderId());
        assertNotNull(BASE_VALID_PARAM.build("短信供应商不存在"), smsProvider);

        smsTemplate.setCreateUserName(BaseContextHandler.getName());

        String type = smsProvider.getType();
        smsTemplateService.saveTemplate(type, smsTemplate);
        return Result.success(smsTemplate);
    }


    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<SmsTemplate> update(@RequestBody SmsTemplate smsTemplate) {
        assertNotNull(BASE_VALID_PARAM.build("模板id不能为空"), smsTemplate.getId());
        valid(smsTemplate);
        smsTemplate.setCode(null);
        smsTemplateService.updateByIdSelective(smsTemplate);
        return Result.success(smsTemplate);
    }

    public void valid(SmsTemplate smsTemplate) {
        assertNotNull(BASE_VALID_PARAM.build("供应商id不能为空"), smsTemplate.getProviderId());
        assertNotEmpty(BASE_VALID_PARAM.build("模板编码不能为空"), smsTemplate.getTemplateCode());
        assertNotEmpty(BASE_VALID_PARAM.build("自定义模板编码不能为空"), smsTemplate.getCode());
        assertNotEmpty(BASE_VALID_PARAM.build("模板名称不能为空"), smsTemplate.getName());
        assertNotEmpty(BASE_VALID_PARAM.build("模板内容不能为空"), smsTemplate.getContent());
        assertNotEmpty(BASE_VALID_PARAM.build("模板签名不能为空"), smsTemplate.getSignName());
    }

    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        smsTemplateService.deleteById(id);
        return Result.success(true);
    }
}