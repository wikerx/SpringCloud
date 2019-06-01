package com.github.zuihou.admin.entity.developer.po;

import com.github.zuihou.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ServiceModuleVal extends BaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 模块id
     *
     * @mbggenerated
     */
    private Long moduleId;

    /**
     * 解析swagger生成json格式字符串
     *
     * @mbggenerated
     */
    private String jsonContent;

    private static final long serialVersionUID = 1L;


}