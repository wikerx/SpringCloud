package com.github.zuihou.controller.msgs;

import java.io.IOException;


import com.github.zuihou.base.Result;
import com.github.zuihou.msgs.entity.MsgsChannel;
import com.github.zuihou.msgs.service.MsgsChannelService;
import com.github.zuihou.msgs.service.MsgsMpnewsService;
import com.github.zuihou.msgs.support.wechat.MessageAPI;
import com.github.zuihou.msgs.support.wechat.entity.Media;
import com.github.zuihou.msgs.support.wechat.entity.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.github.zuihou.commons.exception.core.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertFalse;
import static com.github.zuihou.utils.BizAssert.assertNotNull;
import static com.github.zuihou.utils.BizAssert.assertTrue;


@RestController
@Slf4j
@RequestMapping("/msgsMpnews")
@Api(value = "消息发送任务表", description = "消息发送任务表")
public class MsgsMpnewsController {
    @Autowired
    private MsgsMpnewsService msgsMpnewsService;
    @Autowired
    private MsgsChannelService msgsChannelService;

//    @ApiOperation(value = "查询", notes = "根据id查询")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Result<MsgsMpnews> get(@PathVariable Long id) {
//        return Result.success(msgsMpnewsService.getById(id));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询所有")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Result<List<MsgsMpnews>> find() {
//        return Result.success(msgsMpnewsService.find(null));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询分页")
//    @ApiResponses({})
//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    public Result<PageInfo<MsgsMpnews>> page(@RequestBody OpenApiReq<MsgsMpnews> openApiReq) {
//        MsgsMpnews data = openApiReq.getData();
//        MsgsMpnewsExample example = new MsgsMpnewsExample();
//        MsgsMpnewsExample.Criteria criteria = example.createCriteria();
//        //根据自身业务逻辑写入你的条件。 修改后，请删除该注释
//        //模糊查询请使用：BaseExample.fullLike(data.getXxx)  ，请勿自行拼接('%' + name + '%') !
//        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
//        return Result.success(new PageInfo<>(msgsMpnewsService.find(example)));
//    }
//
//    @ApiOperation(value = "保存", notes = "保存不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public Result<MsgsMpnews> save(@RequestBody MsgsMpnews msgsMpnews) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMpnewsService.saveSelective(msgsMpnews);
//        return Result.success(msgsMpnews);
//    }
//
//    @ApiOperation(value = "修改", notes = "修改不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.PUT)
//    public Result<MsgsMpnews> update(@RequestBody MsgsMpnews msgsMpnews) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMpnewsService.updateByIdSelective(msgsMpnews);
//        return Result.success(msgsMpnews);
//    }
//
//    @ApiOperation(value = "删除", notes = "根据id物理删除")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result<Boolean> delete(@PathVariable Long id) {
//        msgsMpnewsService.deleteById(id);
//        return Result.success(true);
//    }


    /**
     * 用于图文消息内容中的图片上传
     * @param channelId
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result<String> uploadThumb(@RequestParam(value = "channelId") Long channelId,
                                      @ApiParam(name = "file", value = "图文消息缩略图")
                                      @RequestParam(value = "file") MultipartFile file) throws IOException {
        assertFalse(BASE_VALID_PARAM.build("上传缩略图不能为空"), file == null || file.isEmpty());
        assertFalse(BASE_VALID_PARAM.build("上传缩略图大于64K"), file.getSize() > 64 * 1024);
        assertTrue(BASE_VALID_PARAM.build("上传缩略图格式只能为jpg"), "image/jpeg".equals(file.getContentType()) || "image/jpg".equals(file.getContentType()));
        MsgsChannel channel = msgsChannelService.getById(channelId);
        assertNotNull(BASE_VALID_PARAM.build("消息渠道不存在"), channel);

        //64KB，支持JPG格式
        Media media = MessageAPI.mediaUpload(channel.getAccessToken(), MediaType.THUMB, file.getInputStream());
        return Result.result(Integer.parseInt(media.getErrcode()), media.getMediaId(), "");
    }

}