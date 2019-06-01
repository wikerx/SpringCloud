package com.github.zuihou.admin.repository.authority.dao;

import java.util.List;

import com.github.zuihou.admin.entity.authority.po.Role;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends com.github.zuihou.base.dao.BaseAllDao<Long, com.github.zuihou.admin.entity.authority.po.Role, com.github.zuihou.admin.repository.authority.example.RoleExample> {
    List<Role> findRole(@Param("appId") String appId, @Param("applicationId") Long applicationId);
}