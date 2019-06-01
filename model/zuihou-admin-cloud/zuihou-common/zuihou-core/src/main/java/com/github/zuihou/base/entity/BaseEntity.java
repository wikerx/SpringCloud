package com.github.zuihou.base.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * @author zuihou
 * @createTime 2017-12-08 17:34
 */
@Data
public abstract class BaseEntity<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract I getId();

    public abstract void setId(I id);

    protected Date createTime;
    protected Long createUser;
    protected Date updateTime;
    protected Long updateUser;
}
