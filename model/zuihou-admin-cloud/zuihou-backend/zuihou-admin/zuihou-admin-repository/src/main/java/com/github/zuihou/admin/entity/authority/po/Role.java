package com.github.zuihou.admin.entity.authority.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Role extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 角色名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 是否启用
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

    /**
     * 是否删除 
            1：已删除
            0：未删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    private String description;

    private static final long serialVersionUID = 1L;
}