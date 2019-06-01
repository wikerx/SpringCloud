package com.github.zuihou.file.repository.file.dao;

import com.github.zuihou.base.dao.BaseAllDao;
import com.github.zuihou.file.entity.file.po.ZhFile;
import com.github.zuihou.file.repository.file.example.ZhFileExample;

import org.apache.ibatis.annotations.Param;

public interface ZhFileMapper extends BaseAllDao<Long, ZhFile, ZhFileExample> {

    Integer removeDirByAppIdAndId(@Param("appId") String appId, @Param("id") Long id);
}