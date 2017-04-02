package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.service.BaseService;

import java.io.Serializable;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    protected abstract BaseMapper<T, ID> getMapper();

    @Override
    public boolean save(T entity) throws Exception {
        boolean flag = false;
        int i = getMapper().save(entity);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean remove(ID id) throws Exception {
        boolean flag = false;
        int i = getMapper().remove(id);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean removeAll(String... ids) throws Exception {
        boolean flag = false;
        int i = getMapper().removeAll(ids);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean update(T entity) throws Exception {
        boolean flag = false;
        int i = getMapper().update(entity);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public T getByID(ID id) throws Exception{
        return getMapper().getById(id);
    }

}
