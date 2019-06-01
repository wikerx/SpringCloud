package com.github.zuihou.base.service.specific;
import java.io.Serializable;

import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;


public interface BaseSpecificService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> extends SaveSpecificService<I, T, TE>
                 ,UpdateSpecificService<I, T, TE>, DeleteSpecificService<I, T, TE>, RemoveSpecificService<I, T, TE>, SelectSpecificService<I, T, TE>{

}