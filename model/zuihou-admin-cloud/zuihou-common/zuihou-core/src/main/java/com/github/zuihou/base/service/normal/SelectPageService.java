
package com.github.zuihou.base.service.normal;

import java.io.Serializable;
import java.util.List;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.page.PageRequest;

public interface SelectPageService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {



    /**
     * 分页查询数据
     *
     * @param example
     * @param pageRequest
     * @return
     */
    List<T> find(TE example, PageRequest pageRequest);
}
