package com.github.zuihou.base.service.normal;

import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface BaseService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> extends SaveService<I, T, TE>
                 , UpdateService<I, T, TE>, DeleteService<I, T, TE>, RemoveService<I, T, TE>, SelectService<I, T, TE>, SelectPageService<I, T, TE> {

}