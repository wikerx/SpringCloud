package com.github.zuihou.base.service.normal;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface SaveService<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample> {

    /**
     * 保存
     *
     * @param entity
     */
    T save(T entity);

    /**
     * 批量保存
     *
     * @param list
     */
    Collection<T> save(Collection<T> list);

    /**
     * 批量插入， 需要mysql 支持
     *
     * @param list
     * @return
     */
    void batchInsert(List<T> list);

    /***
     * 有选择(不为null)的保存
     * @param entity
     * @return
     */
    T saveSelective(T entity);

    /**
     * id 生成器生成id
     *
     * @return
     */
    I genId();
}
