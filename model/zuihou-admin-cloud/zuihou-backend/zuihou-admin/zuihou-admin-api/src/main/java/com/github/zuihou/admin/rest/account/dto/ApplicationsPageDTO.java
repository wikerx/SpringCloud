package com.github.zuihou.admin.rest.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/09/05
 */
@Data
public class ApplicationsPageDTO extends BaseApplicationsDTO implements Serializable {
    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appId;

}
