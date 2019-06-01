package com.github.zuihou.base.service.specific;

import java.io.Serializable;
import java.util.Collection;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface DeleteSpecificService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {

    
    /**
     * 根据ID物理删除记录
     *
     * @param id
     */
    int deleteByAppIdAndId(String appId, I id);

    /**
     * 批量物理删除记录
     *
     * @param list
     * @return 影响行数
     */
    int deleteByAppIdAndIds(String appId, Collection<I> ids);
}
