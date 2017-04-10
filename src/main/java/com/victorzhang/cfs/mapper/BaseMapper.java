package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.util.query.GenericQueryParam;
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

    int save(List<T> entities) throws Exception;

    int remove(ID id) throws Exception;

    int update(T entity) throws Exception;

    T get(T entity) throws Exception;

    T getById(ID id) throws Exception;

    List<T> list(GenericQueryParam param) throws Exception;

    List<T> list() throws Exception;

    List<Map<String, Object>> listPaging(GenericQueryParam param) throws Exception;

    int count(GenericQueryParam param) throws Exception;
}
