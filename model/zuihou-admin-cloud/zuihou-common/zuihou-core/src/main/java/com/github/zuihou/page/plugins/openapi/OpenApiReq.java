package com.github.zuihou.page.plugins.openapi;

import java.io.Serializable;


import com.github.zuihou.page.AbstractPageRequest;
import com.github.zuihou.page.PageRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zuihou
 * @createTime 2017-12-18 14:53
 */
@ApiModel(value = "OpenApiReq", description = "分页对象入参")
public class OpenApiReq<T extends Serializable> extends AbstractPageRequest implements PageRequest, Serializable {
    /**
     * 第几页   页码从1开始
     */
    @ApiModelProperty(value = "当前页")
    private int pageNo;

    /**
     * 分页大小
     */
    @ApiModelProperty(value = "每页显示数量")
    private int pageSize;
    @ApiModelProperty(value = "具体数据， 属于<泛型>")
    private T data;

    @Override
    public int getPageNo() {
        if (pageNo <= 0) {
            pageNo = FIRST_PAGE;
        }
        return pageNo;
    }

    @Override
    public int getPageSize() {
        if (this.pageSize <= 0) {
            pageSize = DEFAULT_LIMIT;
        }
        return pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
