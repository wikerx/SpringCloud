package com.github.zuihou.controller.msgscenter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.msgs.MsgsRecvType;
import com.github.zuihou.commons.context.DozerUtils;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.msgscenter.domain.MsgsCenterTypeCountDO;
import com.github.zuihou.msgscenter.domain.MsgsNumber;
import com.github.zuihou.msgscenter.dto.MsgsCenterDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageReqDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoSaveDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoUpdateDTO;
import com.github.zuihou.msgscenter.entity.MsgsCenterInfo;
import com.github.zuihou.msgscenter.service.MsgsCenterInfoService;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@RestController
@Slf4j
@RequestMapping("/msgsCenterInfo")
@Api(value = "消息中心 全量表", description = "消息中心 全量表")
public class MsgsCenterInfoController {
    @Autowired
    private MsgsCenterInfoService msgsCenterInfoService;
//    @Autowired
//    private RoleApi roleApi;
//    @Autowired
//    private ApplicationApi applicationApi;
    @Autowired
    private DozerUtils dozerUtils;

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public Result<Boolean> tes1() {
//        log.info("appid=" + BaseContextHandler.getAppId());
//        log.info("userid=" + BaseContextHandler.getUserId());
//        applicationApi.find();
//        return Result.success();
//    }
//
//    @RequestMapping(value = "/test2", method = RequestMethod.GET)
//    public Result<Boolean> tes2() {
//        log.info("appid=" + BaseContextHandler.getAppId());
//        log.info("userid=" + BaseContextHandler.getUserId());
//        roleApi.findUser(1L);
//        return Result.success();
//    }

    /**
     * 读消息并获取跳转地址
     * 用于 前端调用
     *
     * @param msgCenterId 主表id
     * @param receivedId  接收表id
     * @return
     */
    @ApiOperation(value = "读消息并获取跳转地址", notes = "读消息并获取跳转地址")
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public Result<String> updateToReadedAndGetUrl(@RequestParam(value = "msgCenterId") Long msgCenterId,
                                                  @RequestParam(value = "receivedId") Long receivedId) {
        List<Long> ids = Arrays.asList(receivedId);
        msgsCenterInfoService.updateMsgsReadStatus(ids);
        return Result.success(getUrl(msgCenterId));
    }

    /**
     * 根据消息中心id，获取跳转地址
     * @param msgCenterId
     * @return
     */
    private String getUrl(Long msgCenterId) {
//        MsgsCenterInfo info = msgsCenterInfoService.getById(msgCenterId);
//        if (info == null) {
//            return "";
//        }
//
//        String url = info.getHandlerUrl();
//        if (StringUtils.isEmpty(url)) {
//            return "";
//        }
//        log.info("url1={}", url);
//        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
//            Result<ApplicationAllDTO> application = applicationApi.getAppInforByAppId(info.getAppId());
//            log.info("查询应用={}", application);
//            if (application.isSuccess()) {
//                String fullRootPath = application.getData().getFullRootPath();
//                url = WebUtils.linkUri2Uri(fullRootPath, url);
//            }
//        }
//
//        if (StringUtils.isNotEmpty(info.getHandlerParams())) {
//            url = WebUtils.linkUrl2Params(url, info.getHandlerParams());
//        }
//        log.info("url2={}", url);
//        if (url.contains("&") && url.length() == url.lastIndexOf("&") + 1) {
//            url = url.substring(0, url.length() - 1);
//        }
//        log.info("url3={}", url);
//        return url;
        return "";
    }

    /**
     * 用于 前端调用
     *
     * @param msgsCenterIds 主表id 集合
     * @return
     */
    @ApiOperation(value = "删除消息", notes = "删除消息")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result<Boolean> deleteById(@RequestParam(value = "ids[]") Long[] msgsCenterIds) {
        Long userId = BaseContextHandler.getUserId();
        msgsCenterInfoService.deleteMsgs(msgsCenterIds, userId);
        return Result.success();
    }

    /**
     * 用于 前端调用
     *
     * @param receivedIdList 接收表id
     * @return
     */
    @ApiOperation(value = "标记消息为已读", notes = "标记消息为已读")
    @ApiResponses({
            @ApiResponse(code = 75520, message = "id集合不为空"),
    })
    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public Result<Boolean> updateMark(@RequestParam(value = "receivedIdList[]") List<Long> receivedIdList) {
        msgsCenterInfoService.updateMsgsReadStatus(receivedIdList);
        return Result.success();
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<MsgsCenterInfoPageResultDTO>> page(@RequestBody OpenApiReq<MsgsCenterInfoPageReqDTO> openApiReq) {
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(msgsCenterInfoService.find(openApiReq.getData())));
    }

    @ApiOperation(value = "查询数量", notes = "查询所有类型的数量")
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Result<List<MsgsCenterTypeCountDO>> count(@RequestBody MsgsCenterInfoPageReqDTO dto) {
        return Result.success(msgsCenterInfoService.count(dto));
    }

    //------------------------------------------------------------------------------------------------

    /**
     * 用于服务调用
     *
     * @param update
     * @return
     */
    @ApiOperation(value = "待办更新状态", notes = "待办更新状态")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody MsgsCenterInfoUpdateDTO update) {
        msgsCenterInfoService.updateMsgsReadStatus(update);
        return Result.success();
    }

    /**
     * 用于 服务调用
     *
     * @param msgsCenterSaveInfo
     * @return
     */
    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<Long> save(@RequestBody MsgsCenterInfoSaveDTO msgsCenterSaveInfo) {
        //1，验证必要参数
        //原则上来说，这次查询放在 业务代码中更加合理，但考虑到减少服务间的耦合性原则， 故而在此先查询
        if (MsgsRecvType.ROLE.eq(msgsCenterSaveInfo.getRecvType())) {
//            Result<List<Long>> result = roleApi.findUsers(RoleDTO.build(msgsCenterSaveInfo.getRoleAppId(), msgsCenterSaveInfo.getRoleCode()));
//            if (result.isSuccess()) {
//                msgsCenterSaveInfo.setUserIdList(new HashSet<>(result.getData()));
//            }
        }
        return Result.success(msgsCenterInfoService.saveMsgs(msgsCenterSaveInfo));
    }


    @ApiOperation(value = "查询最新的一条消息", notes = "查询最新的一条消息 和 消息数量")
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public Result<MsgsCenterDTO> getLastMsgsAndCount(@RequestParam(value = "userId") Long userId) {

        MsgsNumber count = msgsCenterInfoService.getCount(userId);
        MsgsCenterInfo center = msgsCenterInfoService.getLastMsgs(userId);

        MsgsCenterDTO msgsCenterDTO = dozerUtils.map2(center, MsgsCenterDTO.class);
        if (count != null) {
            msgsCenterDTO.setTotalNum(count.getTotalNum());
            msgsCenterDTO.setNotReadNum(count.getNotReadNum());
        }

        return Result.success(msgsCenterDTO);
    }

    @ApiOperation(value = "根据业务id和业务类型查询所有消息", notes = "根据业务id和业务类型查询所有消息 ")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<MsgsCenterInfoPageResultDTO>> getMsgsByBizId(@RequestParam(value = "bizId", required = false) String bizId,
                                                                    @RequestParam(value = "bizType") String bizType) {
        return Result.success(msgsCenterInfoService.findByBiz(bizType, bizId));
    }

}