package com.github.zuihou.base.dao.normal;

import java.io.Serializable;
import java.util.List;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;

import org.apache.ibatis.session.RowBounds;

public interface Select<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample> {
    /**
     * 根据example拼接的条件，查询记录集
     *
     * @param example 实体操作类
     */
    List<T> selectByExample(TE example, RowBounds rowBounds);
    
    /**
     * 根据example拼接的条件，查询记录集
     *
     * @param example 实体操作类
     */
    List<T> selectByExample(TE example);

    /**
     * 查询单一实体
     *
     * @param example 实体操作类
     * @return
     */
    T selectEntity(TE example);
    

    /**
     * 根据id查询实体
     *
     * @param id 主键
     */
    T selectByPrimaryKey(I id);
    
    /**
     * 根据example拼接的条件，查询数量
     *
     * @param example 实体操作类
     */
    int countByExample(TE example);
}
