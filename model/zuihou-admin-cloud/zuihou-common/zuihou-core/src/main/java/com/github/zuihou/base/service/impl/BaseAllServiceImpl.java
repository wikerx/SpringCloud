package com.github.zuihou.base.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.github.zuihou.base.dao.BaseAllDao;
import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.base.entity.CommonBaseEntity;
import com.github.zuihou.base.service.BaseAllService;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.exception.BizException;

import org.springframework.util.StringUtils;

/**
 * 有appId 的继承这个
 * @author zuihou
 * @createTime 2017-12-08 17:33
 */
public abstract class BaseAllServiceImpl<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample>
        extends BaseServiceImpl<I, T, TE>
        implements BaseAllService<I, T, TE> {

    protected BaseAllDao<I, T, TE> getBaseDao(){
        //向下类型转换   不安全
        return (BaseAllDao<I, T, TE>) getDao();
    }

    @Override
    public T getByAppIdAndId(String appId, I id) {
        if (appId == null || appId.isEmpty() || id == null) {
            return null;
        }
        return getBaseDao().selectByAppIdAndId(appId, id);
    }


    @Override
    public int updateByAppIdAndId(String appId, T entity) {
        if (StringUtils.isEmpty(appId) || entity == null || entity.getId() == null) {
            return 0;
        }
        setUpdateTimes(entity);
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> baseEntity = (CommonBaseEntity<I>) entity;
            baseEntity.setAppId(appId);
        }
        return getBaseDao().updateByAppIdAndId(entity);
    }

    @Override
    public int updateByAppIdAndId(String appId, Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = 0;
        for (T t : list) {
            rows += updateByAppIdAndId(appId, t);
        }
        return rows;
    }

    @Override
    public int updateByAppIdAndIdSelective(String appId, T entity) {
        if (entity == null) {
            return 0;
        }
        setUpdateTimes(entity);
        if (entity instanceof CommonBaseEntity) {
            CommonBaseEntity<I> baseEntity = (CommonBaseEntity<I>) entity;
            baseEntity.setAppId(appId);
        }
        return getBaseDao().updateByAppIdAndIdSelective(entity);
    }

    @Override
    public int updateByAppIdAndIdSelective(String appId, Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = 0;
        for (T t : list) {
            rows += updateByAppIdAndIdSelective(appId, t);
        }
        return rows;
    }

    @Override
    public int deleteByAppIdAndId(String appId, I id) {
        return getBaseDao().deleteByAppIdAndId(appId, id);
    }

    @Override
    public int deleteByAppIdAndIds(String appId, Collection<I> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return getBaseDao().deleteByAppIdAndIds(appId, list);
    }

    @Override
    public int removeByAppIdAndId(String appId, I id) {
        try {
            return getBaseDao().removeByAppIdAndId(appId, id);
        } catch (Exception e) {
            throw new BizException(10000, "无法软删除!");
        }
    }

    @Override
    public int removeByAppIdAndIds(String appId, Collection<I> ids) {
        try {
            return getBaseDao().removeByAppIdAndIds(appId, ids);
        } catch (Exception e) {
            throw new BizException(10000, "无法软删除!");
        }
    }

}
