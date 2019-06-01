package com.github.zuihou.controller.msgs;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.msgs.constant.MsgsChannelType;
import com.github.zuihou.msgs.entity.MsgsBlog;
import com.github.zuihou.msgs.entity.MsgsMobile;
import com.github.zuihou.msgs.entity.MsgsMpnews;
import com.github.zuihou.msgs.entity.MsgsTask;
import com.github.zuihou.msgs.example.MsgsTaskExample;
import com.github.zuihou.msgs.service.MsgsTaskService;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.utils.BizAssert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.commons.exception.core.ExceptionCode.BASE_VALID_PARAM;


@RestController
@Slf4j
@RequestMapping("/msgsTask")
@Api(value = "消息发送任务表", description = "消息发送任务表")
public class MsgsTaskController {
    @Autowired
    private MsgsTaskService msgsTaskService;

    @ApiOperation(value = "查询", notes = "根据id查询")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<MsgsTask> get(@PathVariable Long id) {
        return Result.success(msgsTaskService.getById(id));
    }


    @ApiOperation(value = "查询详情", notes = "根据id查询 查询详情， 适用消息详情页面， 会同时返回任务体和消息内容体")
    @ApiResponses({})
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Result<MsgsTask> getDetail(@PathVariable Long id) {
        return Result.success(msgsTaskService.get(id));
    }

    @ApiOperation(value = "查询", notes = "查询所有")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<MsgsTask>> find() {
        return Result.success(msgsTaskService.find(null));
    }

    @ApiOperation(value = "查询", notes = "查询分页")
    @ApiResponses({})
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<MsgsTask>> page(@RequestBody OpenApiReq<MsgsTask> openApiReq) {
        MsgsTask data = openApiReq.getData();
        MsgsTaskExample example = new MsgsTaskExample();
        MsgsTaskExample.Criteria criteria = example.createCriteria();
        criteria.andChannelIdEqualTo(data.getChannelId())
                .andTypeEqualTo(data.getType())
                .andTitleLike(BaseExample.fullLike(data.getTitle()))
                .andCreateTimeGreaterThanOrEqualToTop(data.getEndTime())
                .andCreateTimeLessThanOrEqualToFooter(data.getStartTime());

        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        return Result.success(new PageInfo<>(msgsTaskService.find(example)));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<MsgsTask> save(@RequestBody MsgsTask msgsTask) {
        //1，验证必要参数
        valid(msgsTask, true);

        msgsTask = msgsTaskService.saveTask(msgsTask);
        return Result.success(msgsTask);
    }

    private void valid(MsgsTask msgsTask, boolean isSave) {
        BizAssert.assertNotNull(BASE_VALID_PARAM, msgsTask.getChannelId());
        BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsTask.getType());
        BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsTask.getTitle());

        if (MsgsChannelType.WECHAT.eq(msgsTask.getType())) {
            BizAssert.assertNotNull(BASE_VALID_PARAM, msgsTask.getMpnewsList());
            List<MsgsMpnews> mpnewsList = msgsTask.getMpnewsList();
            BizAssert.assertTrue(BASE_VALID_PARAM, !mpnewsList.isEmpty());

            mpnewsList.forEach((mpnew) -> {
                BizAssert.assertNotEmpty(BASE_VALID_PARAM, mpnew.getTitle());
                BizAssert.assertNotEmpty(BASE_VALID_PARAM, mpnew.getThumbMediaId());
                BizAssert.assertNotEmpty(BASE_VALID_PARAM, mpnew.getContent());
            });
        } else if (MsgsChannelType.BLOG.eq(msgsTask.getType())) {
            BizAssert.assertNotNull(BASE_VALID_PARAM, msgsTask.getMsgsBlog());
            MsgsBlog msgsBlog = msgsTask.getMsgsBlog();
            if (!isSave) {
                BizAssert.assertNotNull(BASE_VALID_PARAM, msgsBlog.getId());
            }
            BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsBlog.getTitle());
            BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsBlog.getMsgTxt());
            BizAssert.assertTrue(BASE_VALID_PARAM, containUrl(msgsBlog.getMsgTxt()));

        } else if (MsgsChannelType.MOBILE.eq(msgsTask.getType())) {
            BizAssert.assertNotNull(BASE_VALID_PARAM, msgsTask.getMsgsMobile());
            MsgsMobile msgsMobile = msgsTask.getMsgsMobile();
            if (!isSave) {
                BizAssert.assertNotNull(BASE_VALID_PARAM, msgsMobile.getId());
            }
            BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsMobile.getTitle());
            BizAssert.assertNotEmpty(BASE_VALID_PARAM, msgsMobile.getMsgTxt());

        }
    }

    private static boolean containUrl(String url) {
        if (StringUtils.isNotEmpty(url)) {
            return url.contains("http://") || url.contains("https://");
        }
        return false;
    }

    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @ApiResponses({})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result<MsgsTask> update(@RequestBody MsgsTask msgsTask) {
        //1，验证必要参数
        BizAssert.assertNotNull(BASE_VALID_PARAM, msgsTask.getId());
        valid(msgsTask, false);

        //2，尽可能多的设置默认值
        msgsTaskService.update(msgsTask);
        return Result.success(msgsTask);
    }

    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @ApiResponses({})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        msgsTaskService.deleteById(id);
        return Result.success(true);
    }
}