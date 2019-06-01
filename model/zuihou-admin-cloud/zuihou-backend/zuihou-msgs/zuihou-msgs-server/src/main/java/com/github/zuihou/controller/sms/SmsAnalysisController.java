package com.github.zuihou.controller.sms;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DateType;
import com.github.zuihou.commons.constant.DraftStatus;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.sms.constant.SourceType;
import com.github.zuihou.sms.dto.SmsAtlasDTO;
import com.github.zuihou.sms.dto.SmsFindConditionDTO;
import com.github.zuihou.sms.dto.SmsOverviewDTO;
import com.github.zuihou.sms.dto.SmsTop10DTO;
import com.github.zuihou.sms.dto.SmsUsedDTO;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.example.SmsProviderExample;
import com.github.zuihou.sms.example.SmsTaskExample;
import com.github.zuihou.sms.example.SmsTemplateExample;
import com.github.zuihou.sms.service.SmsProviderService;
import com.github.zuihou.sms.service.SmsSendStatusService;
import com.github.zuihou.sms.service.SmsTaskService;
import com.github.zuihou.sms.service.SmsTemplateService;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/28
 */


@RestController
@Slf4j
@RequestMapping("/analysis")
@Api(value = "分析", description = "短信相关分析接口")
public class SmsAnalysisController {

    @Autowired
    private SmsTaskService smsTaskService;
    @Autowired
    private SmsSendStatusService smsSendStatusService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private SmsProviderService smsProviderService;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ApiOperation(value = "首页顶部数量概览", notes = "首页顶部数量概览")
    public Result<SmsOverviewDTO> getOverview() {
        Long userId = BaseContextHandler.getUserId();
        SmsOverviewDTO overview = new SmsOverviewDTO();

        int sendSuccessNum = smsSendStatusService.countSendSuccess(userId);
        overview.setSendSuccessNum(sendSuccessNum);

        SmsTaskExample taskExample = new SmsTaskExample();
        taskExample.createCriteria().andCreateUserEqualTo(userId).andDraftEqualTo(DraftStatus.DRAFT.getVal()).andSourceTypeEqualTo(SourceType.APP.name());
        int draftNum = smsTaskService.count(taskExample);
        overview.setDraftNum(draftNum);

        SmsTemplateExample templateTask = new SmsTemplateExample();
        templateTask.createCriteria().andCreateUserEqualTo(userId);
        int templateNum = smsTemplateService.count(templateTask);
        overview.setTemplateNum(templateNum);


        SmsProviderExample providerExample = new SmsProviderExample();
        providerExample.createCriteria().andCreateUserEqualTo(userId);
        smsProviderService.count(providerExample);
        return Result.success(overview);
    }


    @RequestMapping(value = "/usedNum", method = RequestMethod.POST)
    @ApiOperation(value = "根据日期查询短信使用量", notes = "根据日期查询短信使用量")
    public Result<List<SmsUsedDTO>> findUsedNum(@RequestBody SmsFindConditionDTO param) {
        BizAssert.assertNotNull(ExceptionCode.BASE_VALID_PARAM, param.getStartTime());
        BizAssert.assertNotNull(ExceptionCode.BASE_VALID_PARAM, param.getEndTime());
        param.setUserId(BaseContextHandler.getUserId());
        return Result.success(smsTaskService.findUsedNum(param));
    }

    @RequestMapping(value = "/usedNum/month", method = RequestMethod.POST)
    @ApiOperation(value = "按月查询最近一年短信使用量", notes = "按月查询最近一年短信使用量")
    public Result<List<SmsUsedDTO>> findUsedNumByMonth() {
        SmsFindConditionDTO param = new SmsFindConditionDTO();
        param.setType(DateType.MONTH.name());

        param.setStartTime(DateUtils.localDateTime2Date(LocalDateTime.now().plusYears(-1)));
        param.setEndTime(new Date());
        param.setUserId(BaseContextHandler.getUserId());
        return Result.success(smsTaskService.findUsedNum(param));
    }

    @RequestMapping(value = "/usedNum/day", method = RequestMethod.POST)
    @ApiOperation(value = "按天查询最近一个月短信使用量", notes = "按天查询最近一个月短信使用量")
    public Result<List<SmsUsedDTO>> findUsedNumByDay() {
        SmsFindConditionDTO param = new SmsFindConditionDTO();
        param.setType(DateType.DAY.name());
        param.setStartTime(DateUtils.localDateTime2Date(LocalDateTime.now().plusMonths(-1)));
        param.setEndTime(new Date());
        param.setUserId(BaseContextHandler.getUserId());
        return Result.success(smsTaskService.findUsedNum(param));
    }


    @RequestMapping(value = "/top10", method = RequestMethod.GET)
    @ApiOperation(value = "模板使用量top10", notes = "模板使用量top10")
    public Result<List<SmsTop10DTO>> findTop10() {
        Long userId = BaseContextHandler.getUserId();
        return Result.success(smsTaskService.findTop10(userId));
    }

    @RequestMapping(value = "/appUsed", method = RequestMethod.GET)
    @ApiOperation(value = "应用使用量", notes = "应用使用量")
    public Result<List<SmsUsedDTO>> findAppUsedNum() {
        Long userId = BaseContextHandler.getUserId();

        return Result.success(smsTaskService.findAppUsedNum(userId));
    }

    @RequestMapping(value = "/atlas", method = RequestMethod.GET)
    @ApiOperation(value = "短信图谱", notes = "短信图谱")
    public Result<SmsAtlasDTO> findAtlas(Date startTime, Date endTime, String phone) {
        Long userId = BaseContextHandler.getUserId();

        SmsFindConditionDTO sendParam = new SmsFindConditionDTO();
        sendParam.setStartTime(startTime);
        sendParam.setEndTime(endTime);
        sendParam.setUserId(userId);
        List<SmsTask> sendList = smsTaskService.findSendList(sendParam);

        SmsFindConditionDTO receiveParam = new SmsFindConditionDTO();
        receiveParam.setStartTime(startTime);
        receiveParam.setEndTime(endTime);
        receiveParam.setType(BaseExample.fullLike(phone));
        List<SmsTask> receiveList = smsTaskService.findReceiveList(receiveParam);

        SmsAtlasDTO atlas = new SmsAtlasDTO();
        atlas.setSendList(sendList);
        atlas.setReceiveList(receiveList);
        return Result.success(atlas);
    }

}
