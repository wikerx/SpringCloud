package com.github.zuihou.controller.msgs;


import com.github.zuihou.base.Result;
import com.github.zuihou.commons.context.DozerUtils;
import com.github.zuihou.msgs.dto.MsgsMobileDTO;
import com.github.zuihou.msgs.entity.MsgsMobile;
import com.github.zuihou.msgs.example.MsgsMobileExample;
import com.github.zuihou.msgs.service.MsgsMobileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msgsMobile")
@Api(value = "消息发送任务表", description = "消息发送任务表")
public class MsgsMobileController {
    @Autowired
    private MsgsMobileService msgsMobileService;
    @Autowired
    private DozerUtils dozerUtils;

//    @ApiOperation(value = "查询", notes = "根据id查询")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Result<MsgsMobile> get(@PathVariable Long id) {
//        return Result.success(msgsMobileService.getById(id));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询所有")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Result<List<MsgsMobile>> find() {
//        return Result.success(msgsMobileService.find(null));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询分页")
//    @ApiResponses({})
//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    public Result<PageInfo<MsgsMobile>> page(@RequestBody OpenApiReq<MsgsMobile> openApiReq) {
//        MsgsMobile data = openApiReq.getData();
//        MsgsMobileExample example = new MsgsMobileExample();
//        MsgsMobileExample.Criteria criteria = example.createCriteria();
//        //根据自身业务逻辑写入你的条件。 修改后，请删除该注释
//        //模糊查询请使用：BaseExample.fullLike(data.getXxx)  ，请勿自行拼接('%' + name + '%') !
//        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
//        return Result.success(new PageInfo<>(msgsMobileService.find(example)));
//    }
//
//    @ApiOperation(value = "保存", notes = "保存不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public Result<MsgsMobile> save(@RequestBody MsgsMobile msgsMobile) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMobileService.saveSelective(msgsMobile);
//        return Result.success(msgsMobile);
//    }
//
//    @ApiOperation(value = "修改", notes = "修改不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.PUT)
//    public Result<MsgsMobile> update(@RequestBody MsgsMobile msgsMobile) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMobileService.updateByIdSelective(msgsMobile);
//        return Result.success(msgsMobile);
//    }
//
//    @ApiOperation(value = "删除", notes = "根据id物理删除")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result<Boolean> delete(@PathVariable Long id) {
//        msgsMobileService.deleteById(id);
//        return Result.success(true);
//    }


    @ApiOperation(value = "查询最新的一条消息", notes = "查询最新的一条消息 和 消息数量")
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public Result<MsgsMobileDTO> getLastMobileAndCount(@RequestParam(value = "userId") Long userId) {
        MsgsMobileExample example = new MsgsMobileExample();
        example.setOrderByClause("create_time desc");
        MsgsMobile mobile = msgsMobileService.getUnique(example);

        MsgsMobileDTO msgsCenterDTO = dozerUtils.map2(mobile, MsgsMobileDTO.class);

        int totalNum = msgsMobileService.count(null);//总数
        example.clear();
        example.createCriteria().andCreateUserEqualTo(userId);
        int notReadNum = msgsMobileService.count(example);//已读

        msgsCenterDTO.setTotalNum(totalNum);
        msgsCenterDTO.setReadNum(notReadNum);
        msgsCenterDTO.setNotReadNum(totalNum - notReadNum);//未读

        return Result.success(msgsCenterDTO);
    }
}