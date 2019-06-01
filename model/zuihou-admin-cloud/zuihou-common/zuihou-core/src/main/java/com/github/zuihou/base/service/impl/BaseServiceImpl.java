package com.github.zuihou.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageRowBounds;
import com.github.zuihou.base.dao.BaseNormalDao;
import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.base.entity.CommonBaseEntity;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.base.service.normal.BaseService;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author zuihou
 * @createTime 2017-12-08 17:33
 */
public abstract class BaseServiceImpl<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample>
        implements BaseService<I, T, TE> {

    protected abstract BaseNormalDao<I, T, TE> getDao();

    @Autowired
    private IdGenerate<I> idGenerator;

    @Override
    public I genId() {
        return idGenerator.generate();
    }


    @Override
    public T save(T entity) {
        if (entity == null) {
            throw new BizException("不允许保存空对象");
        }
        setSaveTimes(entity);
        if (entity.getId() == null) {
            entity.setId(genId());
        }
        getDao().insert(entity);
        return entity;
    }

    @Override
    public Collection<T> save(Collection<T> list) {
        if (list == null) {
            throw new BizException("list is null");
        }
        for (T t : list) {
            save(t);
        }
        return list;
    }

    @Override
    public void batchInsert(List<T> list) {
        if (list == null) {
            throw new BizException("list is null");
        }
        list.forEach((val) -> {
            if (val.getId() == null) {
                val.setId(genId());
            }
            setSaveTimes(val);
        });
        getDao().batchInsert(list);
    }


    @Override
    public T saveSelective(T entity) {
        if (entity == null) {
            throw new BizException("不允许保存空对象");

        }
        setSaveTimes(entity);
        injectionId(entity);

        getDao().insertSelective(entity);
        return entity;
    }

    protected void injectionId(T entity) {
        if (entity.getId() == null) {
            entity.setId(genId());
        }
    }

    protected void setSaveTimes(T entity) {
        Long userId = BaseContextHandler.getUserId();

//        if (entity instanceof CommonBaseEntity) {
//            CommonBaseEntity<I> t = (CommonBaseEntity<I>) entity;
//            Date nowDate = new Date();
//            if (t.getCreateTime() == null) {
//                t.setCreateTime(nowDate);
//            }
//            if (t.getUpdateTime() == null) {
//                t.setUpdateTime(nowDate);
//            }
//
//        }
        if (entity.getCreateUser() == null) {
            entity.setCreateUser(userId);
        }
        if (entity.getUpdateUser() == null) {
            entity.setUpdateUser(userId);
        }

        Date nowDate = new Date();
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(nowDate);
        }
        if (entity.getUpdateTime() == null) {
            entity.setUpdateTime(nowDate);
        }

    }

    protected void setUpdateTimes(T entity) {
//        if (entity instanceof CommonBaseEntity) {
//            CommonBaseEntity<I> t = (CommonBaseEntity<I>) entity;
//            Date nowDate = new Date();
//            if (t.getUpdateTime() == null) {
//                t.setUpdateTime(nowDate);
//            }
//        }
        Date nowDate = new Date();
        if (entity.getUpdateTime() == null) {
            entity.setUpdateTime(nowDate);
        }
        if (entity.getUpdateUser() == null) {
            entity.setUpdateUser(BaseContextHandler.getUserId());
        }
    }

    @Override
    public T getUnique(TE example) {
        if (example == null) {
            return null;
        }
        return getDao().selectEntity(example);
    }

    @Override
    public List<T> find(TE example) {
        return getDao().selectByExample(example);
    }

    @Override
    public int count(TE example) {
        return getDao().countByExample(example);
    }

    @Override
    public T getById(I id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Override
    public int updateById(T entity) {
        return doUpdateById(entity, false);
    }

    private int doUpdateById(T entity, boolean isSelectiveUpdate) {
        setUpdateTimes(entity);
        if (entity instanceof CommonBaseEntity) {
            //不允许更改appid
            CommonBaseEntity<I> baseEntity = (CommonBaseEntity<I>) entity;
            String appId = baseEntity.getAppId();
            int rows;
            if (isSelectiveUpdate) {
                baseEntity.setAppId(null);
                rows = getDao().updateByPrimaryKeySelective(entity);
            } else {
                rows = getDao().updateByPrimaryKey(entity);
            }
            baseEntity.setAppId(appId);
            return rows;
        } else {
            if (isSelectiveUpdate) {
                return getDao().updateByPrimaryKeySelective(entity);
            } else {
                return getDao().updateByPrimaryKey(entity);
            }
        }
    }

    @Override
    public int updateByIds(Collection<T> entitys) {
        if (CollectionUtils.isEmpty(entitys)) {
            return 0;
        }
        int rows = 0;
        for (T t : entitys) {
            rows += updateById(t);
        }
        return rows;
    }


    @Override
    public int updateByIdSelective(T entity) {
        return doUpdateById(entity, true);
    }


    @Override
    public int updateByIdSelective(Collection<T> entitys) {
        if (CollectionUtils.isEmpty(entitys)) {
            return 0;
        }
        int rows = 0;
        for (T t : entitys) {
            rows += updateByIdSelective(t);
        }
        return rows;
    }

    @Override
    public int deleteByExample(TE example) {
        return getDao().deleteByExample(example);
    }

    @Override
    public int deleteById(I id) {
        if (StringUtils.isEmpty(id)) {
            return 0;
        }

        return getDao().deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(Collection<I> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        int rows = 0;
        for (I t : ids) {
            rows += deleteById(t);
        }
        return rows;
    }

    @Override
    public int removeById(I id) throws BizException {
        if (StringUtils.isEmpty(id)) {
            return 0;
        }
        return getDao().removeByPrimaryKey(id);
    }

    @Override
    public int removeByIds(Collection<I> ids) throws BizException {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        int rows = 0;
        for (I t : ids) {
            rows += removeById(t);
        }
        return rows;
    }

    @Override
    public List<T> find(TE example, PageRequest pageRequest) {
        return getDao().selectByExample(example, toPageRowBounds(pageRequest));
    }

    protected PageRowBounds toPageRowBounds(PageRequest pageRequest) {
        return new PageRowBounds(pageRequest.getPageNo(), pageRequest.getPageSize());
    }
}
