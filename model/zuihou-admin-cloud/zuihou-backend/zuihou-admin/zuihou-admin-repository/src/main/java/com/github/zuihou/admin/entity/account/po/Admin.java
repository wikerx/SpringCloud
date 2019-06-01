package com.github.zuihou.admin.entity.account.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Admin extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 帐号类型： 0:超级管理 1:普通管理
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 帐号
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 手机
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String logoUrl;

    /**
     * 是否禁用
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

    /**
     * 生日
     *
     * @mbggenerated
     */
    private String birthday;

    /**
     * 性别 1男 2女 0未知
     *
     * @mbggenerated
     */
    private Integer sex;

    private String description;

    private static final long serialVersionUID = 1L;
}