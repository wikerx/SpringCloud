package com.github.zuihou.admin.repository.authority.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.zuihou.admin.constant.ResourcesType;
import com.github.zuihou.admin.entity.authority.po.Resources;
import com.github.zuihou.admin.entity.authority.po.Role;
import com.github.zuihou.admin.entity.authority.po.RoleApplication;
import com.github.zuihou.admin.entity.authority.po.RoleResource;
import com.github.zuihou.admin.repository.authority.dao.ResourcesMapper;
import com.github.zuihou.admin.repository.authority.dao.RoleApplicationMapper;
import com.github.zuihou.admin.repository.authority.dao.RoleMapper;
import com.github.zuihou.admin.repository.authority.dao.RoleResourceMapper;
import com.github.zuihou.admin.repository.authority.example.ResourcesExample;
import com.github.zuihou.admin.repository.authority.example.RoleApplicationExample;
import com.github.zuihou.admin.repository.authority.example.RoleExample;
import com.github.zuihou.admin.repository.authority.example.RoleResourceExample;
import com.github.zuihou.admin.repository.authority.service.AdminRoleService;
import com.github.zuihou.base.dao.BaseAllDao;
import com.github.zuihou.base.service.impl.BaseAllServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import com.github.zuihou.commons.context.CommonConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.JSONUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 10:49
 */
@Service
@Slf4j
public class AdminRoleServiceImpl extends BaseAllServiceImpl<Long, Role, RoleExample> implements AdminRoleService {
    @Autowired
    private RoleMapper adminRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private RoleApplicationMapper applicationRoleMapper;
    @Autowired
    private ResourcesMapper adminResourcesMapper;

    @Override
    protected BaseAllDao<Long, Role, RoleExample> getDao() {
        return adminRoleMapper;
    }


    @Override
    public boolean check(String appId, String code) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andAppIdEqualTo(appId).andCodeEqualTo(code);
        if (adminRoleMapper.countByExample(example) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void authorityAdmin(String appId, Long roleId, List<Long> applicationIdList) {
        RoleApplicationExample example = new RoleApplicationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        applicationRoleMapper.deleteByExample(example);
        Long userId = BaseContextHandler.getUserId();

        List<RoleApplication> ruList = applicationIdList.stream().map((applicationId) -> {
            RoleApplication ru = new RoleApplication();
            ru.setApplicationsId(applicationId);
            ru.setRoleId(roleId);
            ru.setCreateTime(new Date());
            ru.setUpdateTime(new Date());
            ru.setCreateUser(userId);
            ru.setUpdateUser(userId);
            return ru;
        }).collect(Collectors.toList());

        if (!ruList.isEmpty()) {
            applicationRoleMapper.batchInsert(ruList);
        }
    }

    @Override
    public void authorityResources(String appId, String menuGroupCode, Long roleId, List<Long> elementIdList) {
        if (StringUtils.isEmpty(menuGroupCode)) {
            menuGroupCode = CommonConstants.MENU_GROUP_CODE_DEF;
        }

        //删除该角色，该菜单组的所有权限
        RoleResourceExample raExample = new RoleResourceExample();
        raExample.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(raExample);

        //id=1 的 子菜单是id=2 ,授权时，勾选了菜单2，那么菜单1也必须被授权
        //以下代码就是重新计算具有层级关系的菜单和资源
        ResourcesExample rExample = new ResourcesExample();
        rExample.createCriteria().andAppIdEqualTo(appId)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andIsEnableEqualTo(EnableStatus.ENABLE.getVal())
                .andTypeIn(Arrays.asList(ResourcesType.DIR.toString(), ResourcesType.MENU.toString(),
                        ResourcesType.URI.toString(), ResourcesType.BUTTON.toString()))
                .andMenuGroupCodeEqualTo(menuGroupCode);
        //查询 appid + 菜单组下，所有的菜单+资源
        List<Resources> resourcesList = adminResourcesMapper.selectByExample(rExample);
        if (resourcesList.isEmpty()) {
            return;
        }
        //将自定菜单组的所有菜单+资源查询出来， 转换成map (key=id, value=parentId)
        Map<Long, Long> resourceIdMap = resourcesList.stream().
                collect(Collectors.toMap(Resources::getId, Resources::getParentId));
        //list 转 set 去重
        Set<Long> relationResources = elementIdList.stream().collect(Collectors.toSet());
        //计算待授权的资源id的父资源
        elementIdList.stream().forEach((id) -> findParentId(resourceIdMap, relationResources, id));
        log.info("relationResources={}", JSONUtils.toJsonString(relationResources, true));

        Long userId = BaseContextHandler.getUserId();
        List<RoleResource> raList = relationResources.stream()
                .map((resourceId) -> {
                    RoleResource rr = new RoleResource();
                    rr.setRoleId(roleId);
                    rr.setResourceId(resourceId);
                    rr.setUpdateTime(new Date());
                    rr.setCreateTime(new Date());
                    rr.setCreateUser(userId);
                    rr.setUpdateUser(userId);
                    return rr;
                }).collect(Collectors.toList());
        if (!raList.isEmpty()) {
            roleResourceMapper.batchInsert(raList);
        }
    }

    private void findParentId(Map<Long, Long> map, Set<Long> relationResources, Long id) {
        Long parentId = map.get(id);
        if (CommonConstants.PARENT_ID_DEF.equals(parentId) || parentId == null) {
            return;
        }

        relationResources.add(parentId);
        findParentId(map, relationResources, parentId);
    }

    @Override
    public List<Role> findRole(String appId, Long applicationId) {
        return adminRoleMapper.findRole(appId, applicationId);
    }
}
