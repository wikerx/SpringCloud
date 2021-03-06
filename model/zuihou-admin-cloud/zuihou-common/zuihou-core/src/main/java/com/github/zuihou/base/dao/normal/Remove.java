package com.github.zuihou.base.dao.normal;

import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface Remove<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample> {
	/**
     * 根据example拼接的条件，逻辑删除记录
     *
     * @param example 实体操作类
     * @return
     */
    int removeByExample(TE example);

    /**
     * 根据id，逻辑删除记录
     *
     * @param id 主键
     * @return
     */
    int removeByPrimaryKey(I id);
}
