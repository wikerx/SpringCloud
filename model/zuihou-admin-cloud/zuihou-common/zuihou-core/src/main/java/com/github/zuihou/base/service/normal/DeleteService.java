package com.github.zuihou.base.service.normal;

import java.io.Serializable;
import java.util.Collection;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface DeleteService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {

	 /**
     * 根据ID物理删除记录
     *
     * @param id
     */
    int deleteById(I id);
    
    int deleteByExample(TE example);
    
    /**
     * 批量物理删除记录
     *
     * @param list
     * @return 影响行数
     */
    int deleteByIds(Collection<I> ids);
}
