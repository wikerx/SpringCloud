package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:44
 */
@Data
public abstract class BaseApplicationsDTO {
    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appName;

    /**
     * 应用url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 应用类型   APP:应用 SERVER:服务
     * @see AppType
     * @mbggenerated
     */
    private String appType;

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
    /**
     * 是否启用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

}
