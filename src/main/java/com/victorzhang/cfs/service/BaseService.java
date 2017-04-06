package com.victorzhang.cfs.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T, ID extends Serializable> {
    boolean save(T entity) throws Exception;

    boolean remove(ID id) throws Exception;

    boolean removeAll(String... args) throws Exception;

    boolean update(T entity) throws Exception;

    T getById(ID id) throws Exception;

    Map<String, Object> list(Map<String, Object> param) throws Exception;

}
