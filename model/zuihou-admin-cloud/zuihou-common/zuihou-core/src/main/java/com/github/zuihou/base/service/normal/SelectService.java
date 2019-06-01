
package com.github.zuihou.base.service.normal;

import java.io.Serializable;
import java.util.List;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface SelectService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {

	/**
     * 根据ID 查找记录
     *
     * @param id
     */
    T getById(I id);

    /**
     * 查询唯一结果
     * @param example
     * @return
     */
    T getUnique(TE example);

    /**
     * 列表查询
     *
     * @param example
     * @return
     */
    List<T> find(TE example);


    /**
     * 统计
     * @return
     */
    int count(TE example);
}
