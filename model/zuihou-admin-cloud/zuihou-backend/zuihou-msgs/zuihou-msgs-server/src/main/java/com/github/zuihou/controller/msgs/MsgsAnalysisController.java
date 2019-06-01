package com.github.zuihou.controller.msgs;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DraftStatus;
import com.github.zuihou.commons.constant.msgs.TaskStatus;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.msgs.constant.MsgsChannelType;
import com.github.zuihou.msgs.dto.MsgsChannelTypeFindDTO;
import com.github.zuihou.msgs.dto.MsgsDateFindDTO;
import com.github.zuihou.msgs.dto.MsgsOverviewDTO;
import com.github.zuihou.msgs.example.MsgsChannelExample;
import com.github.zuihou.msgs.example.MsgsTaskExample;
import com.github.zuihou.msgs.service.MsgsChannelService;
import com.github.zuihou.msgs.service.MsgsTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/29
 */

@RestController
@Slf4j
@RequestMapping("/msgs/analysis")
@Api(value = "分析", description = "消息相关分析接口")
public class MsgsAnalysisController {
    @Autowired
    private MsgsTaskService msgsTaskService;

    @Autowired
    private MsgsChannelService msgsChannelService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ApiOperation(value = "首页顶部数量概览", notes = "首页顶部数量概览")
    public Result<MsgsOverviewDTO> getOverview() {
        Long userId = BaseContextHandler.getUserId();

        MsgsTaskExample taskExample = new MsgsTaskExample();
        taskExample.createCriteria().andCreateUserEqualTo(userId)
                .andDraftEqualTo(DraftStatus.NOT_DRAFT.getVal())
                .andStatusIn(Arrays.asList(TaskStatus.SUCCESS.getDescribe(), TaskStatus.FAIL.getDescribe()));
        int sendNum = msgsTaskService.count(taskExample);

        taskExample.clear();
        taskExample.createCriteria().andCreateUserEqualTo(userId)
                .andDraftEqualTo(DraftStatus.DRAFT.getVal())
                .andStatusEqualTo(TaskStatus.WAITING.getDescribe());
        int draftNum = msgsTaskService.count(taskExample);

        MsgsChannelExample example = new MsgsChannelExample();
        example.createCriteria().andCreateUserEqualTo(userId);
        int channelNum = msgsChannelService.count(example);

        int channelTypeNum = MsgsChannelType.values().length;

        return Result.success(MsgsOverviewDTO.build(sendNum, draftNum, channelNum, channelTypeNum));
    }

    @ApiImplicitParam(name = "range", value = "类型 all: 查询整个平台的  空：查询自己的", dataType = "string", paramType = "query")
    @RequestMapping(value = "/channelName", method = RequestMethod.GET)
    @ApiOperation(value = "渠道使用情况", notes = "首页渠道使用情况")
    public Result<List<MsgsDateFindDTO>> findNumByChannelName(String range) {
        Long userId = BaseContextHandler.getUserId();
        if (range != null && "all".equals(range)) {
            userId = null;
        }
        return Result.success(msgsTaskService.findNumByChannelName(userId));
    }

    @ApiImplicitParam(name = "range", value = "类型 all: 查询整个平台的  空：查询自己的", dataType = "string", paramType = "query")
    @RequestMapping(value = "/channelType", method = RequestMethod.GET)
    @ApiOperation(value = "渠道类型使用情况", notes = "首页渠道类型使用情况")
    public Result<List<MsgsDateFindDTO>> findNumByChannelType(String range) {
        Long userId = BaseContextHandler.getUserId();
        if (range != null && "all".equals(range)) {
            userId = null;
        }
        return Result.success(msgsTaskService.findNumByChannelType(userId));
    }

//    @RequestMapping(value = "/channelTypeAll", method = RequestMethod.GET)
//    @ApiOperation(value = "系统内所有渠道类型使用情况", notes = "系统整体渠道类型使用情况")
//    public Result<List<MsgsDateFindDTO>> findNumByChannelTypeAll() {
//        return Result.success(msgsTaskService.findNumByChannelType(null));
//    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "range", value = "类型 all: 查询整个平台的  空：查询自己的", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "渠道类型", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "date", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "date", paramType = "query")
    })
    @RequestMapping(value = "/num", method = RequestMethod.GET)
    @ApiOperation(value = "查询日期区间内，指定类型、指定日期的发送数量", notes = "查询日期区间内，指定类型（微信、微博、移动端）、指定日期（月、周、日）的发送数量（总量、成功、失败、待发送量）")
    public Result<List<MsgsChannelTypeFindDTO>> findNum(String range, String type, Date startTime, Date endTime) {
        Long userId = BaseContextHandler.getUserId();
        if (range != null && "all".equals(range)) {
            userId = null;
        }
        return Result.success(msgsTaskService.findNumByDate(type, userId, startTime, endTime));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "range", value = "类型 all: 查询整个平台的  空：查询自己的", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "渠道类型", dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = "/failNum", method = RequestMethod.GET)
    @ApiOperation(value = "查询指定范围、指定类型的失败发送top10", notes = "查询指定范围、指定类型的失败发送top10")
    public Result<List<MsgsChannelTypeFindDTO>> ge(String range, String type) {
        Long userId = BaseContextHandler.getUserId();
        if (range != null && "all".equals(range)) {
            userId = null;
        }
        return Result.success(msgsTaskService.findFailNumByType(type, userId));
    }

}
