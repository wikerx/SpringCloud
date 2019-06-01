package com.github.zuihou.base.dao.normal;

import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @param <I>
 * @param <T>
 * @param <TE>
 * @author zuihou
 */
public interface Update<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample> {
	/**
     * 根据example拼接的条件，修改不为null的字段
     *
     * @param record  实体
     * @param example 实体操作类
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") TE example);

    /**
     * 根据id，修改不为null的字段
     *
     * @param record 实体
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据example拼接的条件，覆盖修改所有字段
     *
     * @param record  实体
     * @param example 实体操作类
     */
    int updateByExample(@Param("record") T record, @Param("example") TE example);

    /**
     * 根据id，覆盖修改所有字段
     *
     * @param record 实体
     */
    int updateByPrimaryKey(T record);
}
