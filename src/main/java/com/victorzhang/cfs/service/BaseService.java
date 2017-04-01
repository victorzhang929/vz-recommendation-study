package com.victorzhang.cfs.service;

import java.io.Serializable;

public interface BaseService<T, ID extends Serializable> {
    boolean save(T entity);

    boolean remove(ID id);

    boolean removeAll(String... args);

    boolean update(T entity);

    T getByID(ID id);
}
