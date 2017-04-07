package com.victorzhang.cfs.service;

import com.victorzhang.cfs.util.query.GenericQueryParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T, ID extends Serializable> {
    boolean save(T entity) throws Exception;

    boolean remove(ID id) throws Exception;

    boolean removeAll(List<String> ids) throws Exception;

    boolean update(T entity) throws Exception;

    T getById(ID id) throws Exception;

    List<T> list(T entity) throws Exception;

    Map<String, Object> listPaging(T entity, String page, String pageSize, String startDate, String endDate) throws Exception;

    int count(T entity) throws Exception;

    int count(GenericQueryParam param) throws Exception;
}
