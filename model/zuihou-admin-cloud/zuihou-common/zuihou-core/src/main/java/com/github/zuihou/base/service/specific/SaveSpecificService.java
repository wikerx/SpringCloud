package com.github.zuihou.base.service.specific;

import java.io.Serializable;
import java.util.Collection;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface SaveSpecificService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {

	/**
     * 保存
     * @param entity
     */
    T save(T entity);

    /**
     * 批量保存
     * @param list
     */
    Collection<T> save(Collection<T> list);

    /***
     * 有选择(不为null)的保存
     * @param entity
     * @return
     */
    T saveSelective(T entity);
    
    /**
     * id 生成器生成id
     * @return
     */
    I genId();
}
