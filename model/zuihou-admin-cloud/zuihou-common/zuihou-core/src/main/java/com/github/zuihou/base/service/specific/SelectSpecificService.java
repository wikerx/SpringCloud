
package com.github.zuihou.base.service.specific;

import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface SelectSpecificService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {

    /**
     * 根据ID + AppId查找记录
     *
     * @param id
     */
    T getByAppIdAndId(String appId, I id);
}
