package com.victorzhang.cfs.mapper;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Definite BaseMapper Class
 * Created by victorzhang on 2017/3/31.
 */
@Repository
public interface BaseMapper<T, ID extends Serializable> {
    int save(T entity);

    int remove(ID id);

    int removeAll(String... ids);

    int update(T entity);

    T getById(ID id);
}
