package com.github.zuihou.admin.entity.developer.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ServiceResource extends CommonBaseEntity<Long> implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 所属应用appid
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 功能模块id
     *
     * @mbggenerated
     */
    private Long moduleId;

    /**
     * 父id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 功能描述
     *
     * @mbggenerated
     */
    private String describe;

    /**
     * 路径
     *
     * @mbggenerated
     */
    private String basePath;

    /**
     * 资源主机
     *
     * @mbggenerated
     */
    private String host;

    /**
     * api路径
     *
     * @mbggenerated
     */
    private String uri;

    /**
     * get/post/all
     *
     * @mbggenerated
     */
    private String httpMethod;

    /**
     * 状态： 0启用 1禁用
     *
     * @mbggenerated
     */
    private Boolean status;

    /**
     * 是否公有资源 所有人可调用(无需申请)
     *
     * @mbggenerated
     */
    private Boolean isPublic;

    /**
     * 序号
     *
     * @mbggenerated
     */
    private Integer orderNo;


    private static final long serialVersionUID = 1L;
}