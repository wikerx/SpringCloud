package com.github.zuihou.controller.msgs;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.msgs.entity.MsgsChannel;
import com.github.zuihou.msgs.example.MsgsChannelExample;
import com.github.zuihou.msgs.service.MsgsChannelService;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.utils.BizAssert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.commons.exception.core.ExceptionCode.BASE_VALID_PARAM;


@RestController
@Slf4j
@RequestMapping("/msgsChannel")
@Api(value = "消息短信邮件渠道表", description = "消息短信邮件渠道表")
public class MsgsChannelController {
    @Autowired
    private MsgsChannelService msgsChannelService;

    @ApiOperation(value = "查询", notes = "根据id查询")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<MsgsChannel> get(@PathVariable Long id) {
        return Result.success(msgsChannelService.getById(id));
    }

    @ApiOperation(value = "查询", notes = "查询所有")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<MsgsChannel>> find() {
        Long userId = BaseContextHandler.getUserId();
        MsgsChannelExample example = new MsgsChannelExample();
        example.createCriteria().andCreateUserEqualTo(userId);
        return Result.success(msgsChannelService.find(example));
    }

    @ApiOperation(value = "根据类型查询所有", notes = "根据类型查询所有")
    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
    public Result<List<MsgsChannel>> findByType(@PathVariable String type) {
        Long userId = BaseContextHandler.getUserId();
        MsgsChannelExample example = new MsgsChannelExample();
        example.createCriteria().andCreateUserEqualTo(userId).andTypeEqualTo(type);
        return Result.success(msgsChannelService.find(example));
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<MsgsChannel>> page(@RequestBody OpenApiReq<MsgsChannel> openApiReq) {
        MsgsChannel data = openApiReq.getData();
        MsgsChannelExample example = new MsgsChannelExample();
        MsgsChannelExample.Criteria criteria = example.createCriteria();
        criteria.andAppIdLike(BaseExample.fullLike(data.getAppId()))
                .andAppSecretLike(BaseExample.fullLike(data.getAppSecret()))
                .andNameLike(BaseExample.fullLike(data.getName()))
                .andTypeEqualTo(data.getType())
                .andCreateTimeLessThanOrEqualToFooter(data.getEndTime())
                .andCreateTimeGreaterThanOrEqualToTop(data.getStartTime());
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(msgsChannelService.find(example)));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<MsgsChannel> save(@RequestBody MsgsChannel msgsChannel) {
        //1，验证必要参数
        valid(msgsChannel);

        msgsChannel.setCreateUserName(BaseContextHandler.getName());
        msgsChannelService.saveSelective(msgsChannel);

        //微信类型 需要刷新token
        msgsChannelService.refreshWechatToken(new Long[]{msgsChannel.getId()});
        return Result.success(msgsChannel);
    }

    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<MsgsChannel> update(@RequestBody MsgsChannel msgsChannel) {
        //1，验证必要参数
        BizAssert.assertNotNull(BASE_VALID_PARAM, msgsChannel.getId());
        valid(msgsChannel);
        //2，尽可能多的设置默认值
        msgsChannelService.updateByIdSelective(msgsChannel);

        //微信类型 需要刷新token
        msgsChannelService.refreshWechatToken(new Long[]{msgsChannel.getId()});
        return Result.success(msgsChannel);
    }

    private void valid(MsgsChannel msgsChannel) {
        BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsChannel.getAppId());
        BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsChannel.getAppSecret());
        BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsChannel.getType());
    }

    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        msgsChannelService.deleteById(id);
        return Result.success(true);
    }

    @ApiOperation(value = "刷新微信token", notes = "刷新微信token")
    @RequestMapping(value = "/refresh", method = RequestMethod.PUT)
    public Result<Boolean> updateWechatToken(@RequestParam(value = "ids[]") Long[] ids) {
        msgsChannelService.refreshWechatToken(ids);
        return Result.success(true);
    }
}