package com.github.zuihou.admin.entity.account.po;

import java.io.Serializable;

import com.github.zuihou.base.entity.CommonBaseEntity;

import lombok.Data;

@Data
public class Applications extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appName;

    /**
     * 应用类型   APP:应用 SERVER:服务
     *
     * @mbggenerated
     */
    private String appType;

    /**
     * 启用状态
     * 1：启用
     * 0：禁用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

    /**
     * 是否删除
     * 1：已删除
     * 0：未删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 开发者密码是校验开发者身份的密码，具有极高的安全性
     *
     * @mbggenerated
     */
    private String appSecret;

    /**
     * 应用url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 应用logo
     *
     * @mbggenerated
     */
    private String logoUrl;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String comment;

    /**
     * 是否所有人能访问 1是 0否
     *
     * @mbggenerated
     */
    private Boolean isPublic;

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    private Integer orderNum;

    private static final long serialVersionUID = 1L;

}