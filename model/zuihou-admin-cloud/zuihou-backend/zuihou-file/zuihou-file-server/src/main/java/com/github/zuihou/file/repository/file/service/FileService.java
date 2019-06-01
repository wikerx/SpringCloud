package com.github.zuihou.file.repository.file.service;

import com.github.zuihou.base.service.BaseAllService;
import com.github.zuihou.file.entity.file.po.ZhFile;
import com.github.zuihou.file.repository.file.example.ZhFileExample;

/**
 * @author zuihou
 * @createTime 2018-01-26 23:05
 */
public interface FileService extends BaseAllService<Long, ZhFile, ZhFileExample> {
    /**
     * 根据appId和 tree_path 删除文件夹
     *
     * @param appId
     * @param id
     */
    Integer removeDirByAppIdAndId(String appId, Long id);

}
