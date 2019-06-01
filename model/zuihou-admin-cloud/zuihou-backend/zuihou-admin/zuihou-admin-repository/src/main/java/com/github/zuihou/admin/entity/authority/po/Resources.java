package com.github.zuihou.admin.entity.authority.po;

import com.github.zuihou.base.entity.CommonBaseEntity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Resources extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 资源编码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 资源类型为菜单时
     *
     * @mbggenerated
     */
    private String menuGroupCode;

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 资源类型 MENU:菜单 DIR:目录 BUTTON:按钮
     * BUTTON:按钮 URI:页面上的url
     * API:api
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 菜单url
     *
     * @mbggenerated
     */
    private String href;

    /**
     * 资源请求方式 POST/GET/PUT/DELETE
     *
     * @mbggenerated
     */
    private String method;

    /**
     * 菜单打开方式  _self：相同框架 _top：整页  _blank：新建一个窗口 _paren：t父窗口
     *
     * @mbggenerated
     */
    private String targets;

    /**
     * 菜单图标 可以是http地址， 可以是图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer orderNum;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 是否公共资源 1是 0否
     *
     * @mbggenerated
     */
    private Boolean isPublic;

    /**
     * 层级路径  顶级默认:,   [父path]父code,
     *
     * @mbggenerated
     */
    private String path;

    /**
     * 是否启用
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

    private static final long serialVersionUID = 1L;
}