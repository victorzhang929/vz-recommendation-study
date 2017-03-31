package com.victorzhang.cfs.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Definite BaseMapper Class
 * Created by victorzhang on 2017/3/31.
 */
public interface BaseMapper<T, ID extends Serializable> {
    void save(T entity);

    void remove(ID id);

    void update(T entity);

    List<T> list(Map<String, Object> map);

    T get(Map<String, Object> map, String... args);

    int count(Map<String, Object> map);
}
