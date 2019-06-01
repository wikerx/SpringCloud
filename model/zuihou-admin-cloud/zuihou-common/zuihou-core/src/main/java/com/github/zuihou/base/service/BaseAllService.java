package com.github.zuihou.base.service;

import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.base.service.normal.BaseService;
import com.github.zuihou.base.service.specific.BaseSpecificService;
import com.github.zuihou.example.BaseExample;

/**
 * @author zuihou
 * @createTime 2017-12-08 17:30
 */
public interface BaseAllService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> extends BaseService<I, T, TE>
										, BaseSpecificService<I, T, TE> {

}
