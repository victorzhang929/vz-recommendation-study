package com.victorzhang.cfs.service;

import com.victorzhang.cfs.util.query.GenericQueryParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T, ID extends Serializable> {
    boolean save(T entity) throws Exception;

    boolean save(List<T> entities) throws Exception;

    boolean remove(ID id) throws Exception;

    boolean update(T entity) throws Exception;

    T get(T entity) throws Exception;

    T getById(ID id) throws Exception;

    List<T> list(T entity) throws Exception;

    List<T> list() throws Exception;

    Map<String, Object> listPaging(T entity, String page, String pageSize, String startDate, String endDate, GenericQueryParam param) throws Exception;

    int count(T entity) throws Exception;

    int count(GenericQueryParam param) throws Exception;
}
