package com.github.zuihou.admin.repository.authority.service.impl;

import java.util.List;

import com.github.zuihou.admin.constant.ResourcesType;
import com.github.zuihou.admin.entity.authority.po.Resources;
import com.github.zuihou.admin.repository.authority.dao.ResourcesMapper;
import com.github.zuihou.admin.repository.authority.example.ResourcesExample;
import com.github.zuihou.admin.repository.authority.service.AdminResourcesService;
import com.github.zuihou.base.dao.BaseAllDao;
import com.github.zuihou.base.service.impl.BaseAllServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:16
 */
@Service
public class AdminResourcesServiceImpl extends BaseAllServiceImpl<Long, Resources, ResourcesExample> implements AdminResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    protected BaseAllDao<Long, Resources, ResourcesExample> getDao() {
        return resourcesMapper;
    }


    @Override
    public boolean check(String appId, String code) {
        ResourcesExample example = new ResourcesExample();
        example.createCriteria().andAppIdEqualTo(appId).andCodeEqualTo(code)
                .andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        if (super.count(example) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkChildren(String appId, Long id) {
        if (resourcesMapper.countChildren(appId, id) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkMenu(String appId, Long menuGroupId) {
        if (resourcesMapper.checkMenu(appId, menuGroupId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Resources> findResources(String appId) {
        List<ResourcesType> resourcesTypeList = ResourcesType.listResourcesType();
        return resourcesMapper.findResourcesByApplicationId(appId, null, resourcesTypeList);
    }

    @Override
    public List<Resources> findMenu(String appId) {
        List<ResourcesType> menuTypeList = ResourcesType.listMenuType();
        return resourcesMapper.findResourcesByApplicationId(appId, null, menuTypeList);
    }
}
