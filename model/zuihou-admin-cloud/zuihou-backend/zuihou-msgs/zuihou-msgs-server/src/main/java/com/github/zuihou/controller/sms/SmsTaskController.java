package com.github.zuihou.controller.sms;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DraftStatus;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.manager.SmsManager;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.sms.constant.SourceType;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.example.SmsTaskExample;
import com.github.zuihou.sms.service.SmsTaskService;

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
import static com.github.zuihou.utils.BizAssert.assertNotNull;


@RestController
@Slf4j
@RequestMapping("/smsTask")
@Api(value = "发送任务表", description = "发送任务表")
public class SmsTaskController {
    @Autowired
    private SmsTaskService smsTaskService;
    @Autowired
    private SmsManager smsManager;
    @ApiOperation(value = "查询", notes = "根据id查询")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<SmsTask> get(@PathVariable Long id) {
        return Result.success(smsTaskService.get(id));
    }

    @ApiOperation(value = "查询", notes = "查询所有")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<SmsTask>> find() {
        return Result.success(smsTaskService.find(null));
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<SmsTask>> page(@RequestBody OpenApiReq<SmsTask> openApiReq) {
        SmsTask data = openApiReq.getData();
        SmsTaskExample example = new SmsTaskExample();
        SmsTaskExample.Criteria criteria = example.createCriteria();

        if (data.getDraft() == null) {
            data.setDraft(DraftStatus.NOT_DRAFT.getVal());
        }
        criteria.andProviderIdEqualTo(data.getProviderId())
                .andTopicLike(BaseExample.fullLike(data.getTopic()))
                .andSendTimeLessThanOrEqualToFooter(data.getEndTime())
                .andSendTimeGreaterThanOrEqualToTop(data.getStartTime())
                .andDraftEqualTo(data.getDraft())
        ;

        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(smsTaskService.find(example)));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<SmsTask> save(@RequestBody SmsTask smsTask) {
        smsTask.setSourceType(SourceType.APP.name());
        smsTask.setAppName(BaseContextHandler.getAppName());
        smsTask.setAppId(BaseContextHandler.getAppId());
        smsManager.saveTask(smsTask);
        return Result.success(smsTask);
    }

    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<SmsTask> update(@RequestBody SmsTask smsTask) {
        assertNotNull(BASE_VALID_PARAM.build("任务id不能为空"), smsTask.getId());
        assertNotNull(BASE_VALID_PARAM.build("供应商id不能为空"), smsTask.getProviderId());
        assertNotNull(BASE_VALID_PARAM.build("模板id不能为空"), smsTask.getTemplateId());
        assertNotNull(BASE_VALID_PARAM.build("短信主题不能为空"), smsTask.getTopic());
        //2，尽可能多的设置默认值
        smsTaskService.update(smsTask);
        return Result.success(smsTask);
    }

    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        smsTaskService.deleteById(id);
        return Result.success(true);
    }
}