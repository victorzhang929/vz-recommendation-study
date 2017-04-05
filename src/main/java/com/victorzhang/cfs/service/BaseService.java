package com.victorzhang.cfs.service;

import java.io.Serializable;

public interface BaseService<T, ID extends Serializable> {
    boolean save(T entity) throws Exception;

    boolean remove(ID id) throws Exception;

    boolean removeAll(String... args) throws Exception;

    boolean update(T entity) throws Exception;

    T getByID(ID id) throws Exception;

}
