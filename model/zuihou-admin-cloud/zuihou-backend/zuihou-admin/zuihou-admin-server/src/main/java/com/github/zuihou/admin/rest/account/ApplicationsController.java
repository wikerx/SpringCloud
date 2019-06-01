package com.github.zuihou.admin.rest.account;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.admin.repository.account.example.ApplicationsExample;
import com.github.zuihou.admin.repository.account.service.ApplicationsService;
import com.github.zuihou.admin.rest.account.api.ApplicationsApi;
import com.github.zuihou.admin.rest.account.dto.ApplicationsDTO;
import com.github.zuihou.admin.rest.account.dto.ApplicationsPageDTO;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.context.DozerUtils;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:06
 */
@Api(value = "API - ApplicationsApiImpl", description = "第三方应用管理")
@Slf4j
@RestController
@RequestMapping("/app")
public class ApplicationsController implements ApplicationsApi {
    @Autowired
    private ApplicationsService applicationService;
    @Autowired
    private DozerUtils dozerUtils;

    /**
     * 根据appid 和 密码 查找应用
     *
     * @param appId     appId
     * @param appSecret appSecret
     * @return
     */
    @Override
    @ApiOperation(value = "根据appid和appsecret查找应用", notes = "根据appid和appsecret查找应用")
    @RequestMapping(value = "/{appId}/{appSecret}", method = RequestMethod.GET)
    public Result<ApplicationsDTO> getBySecret(@PathVariable(value = "appId") String appId, @PathVariable(value = "appSecret") String appSecret) {
        return Result.success(dozerUtils.map(applicationService.getBySecret(appId, appSecret), ApplicationsDTO.class));
    }

    @ApiOperation(value = "获取用户拥有的指定类型的应用", notes = "获取当前登录用户所用于用户")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Result<List<ApplicationsDTO>> findAppIdList(@RequestParam(value = "typeList", required = false) Long[] typeList) {
        //1、获取基础信息
        //type==1:只查应用;type==2:只查服务
        Long userId = BaseContextHandler.getUserId();

        //2、查询该角色拥有的资源
//        List<ApplicationsDTO> appShowDtos = dozerUtils.mapList(applicationService.findAppByAdmin(userId, type), ApplicationsDTO.class);

        //3、返回
//        return Result.success(appShowDtos);
        return Result.success(null);
    }

    @ApiOperation(value = "获取所有应用", notes = "获取所有应用")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<PageInfo<ApplicationsDTO>> page(@RequestBody OpenApiReq<ApplicationsPageDTO> openApiReq) {
        ApplicationsPageDTO page = openApiReq.getData();
        //2.查询所有应用
        ApplicationsExample example = new ApplicationsExample();
        if (page != null) {
            ApplicationsExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotEmpty(page.getAppId())) {
                criteria.andAppIdLike(ApplicationsExample.fullLike(page.getAppId()));
            }
            if (StringUtils.isNotEmpty(page.getAppName())) {
                criteria.andAppNameLike(ApplicationsExample.fullLike(page.getAppName()));
            }
            if (StringUtils.isNotEmpty(page.getAppType())) {
                criteria.andAppTypeEqualTo(page.getAppType());
            }
            if (StringUtils.isNotEmpty(page.getComment())) {
                criteria.andCommentLike(ApplicationsExample.fullLike(page.getComment()));
            }
            if (StringUtils.isNotEmpty(page.getUrl())) {
                criteria.andUrlLike(ApplicationsExample.fullLike(page.getUrl()));
            }
            if (page.getIsEnable() != null) {
                criteria.andIsEnableEqualTo(page.getIsEnable());
            }
            if (page.getIsPublic() != null) {
                criteria.andIsPublicEqualTo(page.getIsPublic());
            }
        }
        example.setOrderByClause("update_time desc");
        //TODO 后期加上权限
        //1.设置分页器
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        List list = dozerUtils.mapPage(applicationService.find(example), ApplicationsDTO.class);
        return Result.success(new PageInfo<>(list));
    }



}
