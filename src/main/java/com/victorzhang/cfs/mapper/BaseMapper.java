package com.victorzhang.cfs.mapper;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Definite BaseMapper Class
 * Created by victorzhang on 2017/3/31.
 */
@Repository
public interface BaseMapper<T, ID extends Serializable> {
    int save(T entity) throws Exception;

    int remove(ID id) throws Exception;

    int removeAll(String... ids) throws Exception;

    int update(T entity) throws Exception;

    T getById(ID id) throws Exception;

    int countById(ID id) throws Exception;

    List<Map<String, Object>> listById(ID id) throws Exception;
}
