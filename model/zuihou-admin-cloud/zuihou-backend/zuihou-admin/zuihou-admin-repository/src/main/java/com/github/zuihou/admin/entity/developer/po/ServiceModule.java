package com.github.zuihou.admin.entity.developer.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ServiceModule extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 所属应用appid
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 图标
     *
     * @mbggenerated
     */
    private String logo;

    /**
     * 功能名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 功能编码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 功能描述
     *
     * @mbggenerated
     */
    private String describe;

    /**
     * 状态： 0、正常使用中   1、已过时（有新版本取代）     2、停止使用
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 联系人邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 模块类型 数据字典取
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 是否公有模块 所有人可调用(无需申请)
     *
     * @mbggenerated
     */
    private Boolean publicIs;

    /**
     * 是否公开模块   需要登陆才可以看到。
     *
     * @mbggenerated
     */
    private Boolean publicLook;

    /**
     * API接口文档地址
     *
     * @mbggenerated
     */
    private String apiUrl;

    /**
     * 资源数量（接口数量）
     *
     * @mbggenerated
     */
    private Integer resourceNum;

    private static final long serialVersionUID = 1L;
}