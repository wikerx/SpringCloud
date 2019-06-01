package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:43
 */
@Data
public class ApplicationsDTO extends BaseApplicationsDTO implements Serializable {
    private Long id;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
