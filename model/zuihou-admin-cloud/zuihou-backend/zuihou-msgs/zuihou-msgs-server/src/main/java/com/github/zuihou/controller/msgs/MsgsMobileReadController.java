package com.github.zuihou.controller.msgs;


import com.github.zuihou.msgs.service.MsgsMobileReadService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msgsMobileRead")
@Api(value = "", description = "")
public class MsgsMobileReadController {
    @Autowired
    private MsgsMobileReadService msgsMobileReadService;

//    @ApiOperation(value = "查询", notes = "根据id查询")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Result<MsgsMobileRead> get(@PathVariable Long id) {
//        return Result.success(msgsMobileReadService.getById(id));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询所有")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Result<List<MsgsMobileRead>> find() {
//        return Result.success(msgsMobileReadService.find(null));
//    }
//
//    @ApiOperation(value = "查询", notes = "查询分页")
//    @ApiResponses({})
//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    public Result<PageInfo<MsgsMobileRead>> page(@RequestBody OpenApiReq<MsgsMobileRead> openApiReq) {
//        MsgsMobileRead data = openApiReq.getData();
//        MsgsMobileReadExample example = new MsgsMobileReadExample();
//        MsgsMobileReadExample.Criteria criteria = example.createCriteria();
//        //根据自身业务逻辑写入你的条件。 修改后，请删除该注释
//        //模糊查询请使用：BaseExample.fullLike(data.getXxx)  ，请勿自行拼接('%' + name + '%') !
//        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
//        return Result.success(new PageInfo<>(msgsMobileReadService.find(example)));
//    }
//
//    @ApiOperation(value = "保存", notes = "保存不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public Result<MsgsMobileRead> save(@RequestBody MsgsMobileRead msgsMobileRead) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMobileReadService.saveSelective(msgsMobileRead);
//        return Result.success(msgsMobileRead);
//    }
//
//    @ApiOperation(value = "修改", notes = "修改不为空的字段")
//    @ApiResponses({})
//    @RequestMapping(value = "", method = RequestMethod.PUT)
//    public Result<MsgsMobileRead> update(@RequestBody MsgsMobileRead msgsMobileRead) {
//        //1，验证必要参数
//        //2，尽可能多的设置默认值
//        msgsMobileReadService.updateByIdSelective(msgsMobileRead);
//        return Result.success(msgsMobileRead);
//    }
//
//    @ApiOperation(value = "删除", notes = "根据id物理删除")
//    @ApiResponses({})
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public Result<Boolean> delete(@PathVariable Long id) {
//        msgsMobileReadService.deleteById(id);
//        return Result.success(true);
//    }
}