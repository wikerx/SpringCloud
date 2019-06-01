package com.github.zuihou.admin.repository.authority.dao;

import java.util.List;

import com.github.zuihou.admin.constant.ResourcesType;
import com.github.zuihou.admin.entity.authority.po.Resources;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesMapper extends com.github.zuihou.base.dao.BaseAllDao<Long, com.github.zuihou.admin.entity.authority.po.Resources, com.github.zuihou.admin.repository.authority.example.ResourcesExample> {
    /**
     * 查询子菜单和子资源数量
     *
     * @param appId
     * @param id
     * @return
     */
    int countChildren(@Param("appId") String appId, @Param("id") Long id);

    int checkMenu(@Param("appId") String appId, @Param("menuGroupId") Long menuGroupId);

    List<Resources> findResourcesByApplicationId(@Param("appId") String appId,
                                                 @Param("menuGroupCode") String menuGroupCode,
                                                 @Param("resourcesTypeList") List<ResourcesType> resourcesTypeList);
}