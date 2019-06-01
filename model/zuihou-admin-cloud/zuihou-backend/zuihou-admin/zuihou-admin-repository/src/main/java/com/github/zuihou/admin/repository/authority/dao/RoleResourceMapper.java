package com.github.zuihou.admin.repository.authority.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleResourceMapper extends com.github.zuihou.base.dao.BaseNormalDao<Long,
        com.github.zuihou.admin.entity.authority.po.RoleResource,
        com.github.zuihou.admin.repository.authority.example.RoleResourceExample> {
}