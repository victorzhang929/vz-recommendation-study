package com.victorzhang.recommendation.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.victorzhang.recommendation.mapper.BaseMapper;
import com.victorzhang.recommendation.service.BaseService;
import com.victorzhang.recommendation.util.CommonUtils;
import com.victorzhang.recommendation.util.query.BuildQueryParam;
import com.victorzhang.recommendation.util.query.GenericQueryParam;
import org.apache.commons.lang3.StringUtils;

import static com.victorzhang.recommendation.util.Constants.BEGIN;
import static com.victorzhang.recommendation.util.Constants.DATA;
import static com.victorzhang.recommendation.util.Constants.EMPTY_STRING;
import static com.victorzhang.recommendation.util.Constants.END_DATE;
import static com.victorzhang.recommendation.util.Constants.END_DATE_FORMAT;
import static com.victorzhang.recommendation.util.Constants.PAGE_SIZE;
import static com.victorzhang.recommendation.util.Constants.START_DATE;
import static com.victorzhang.recommendation.util.Constants.START_DATE_FORMAT;

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
    public boolean save(List<T> entities) throws Exception {
        boolean flag = false;
        int i = getMapper().save(entities);
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
    public boolean update(T entity) throws Exception {
        boolean flag = false;
        int i = getMapper().update(entity);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public T get(T entity) throws Exception {
        return getMapper().get(entity);
    }

    @Override
    public T getById(ID id) throws Exception {
        return getMapper().getById(id);
    }

    @Override
    public List<T> list(T entity) throws Exception {
        GenericQueryParam param = new GenericQueryParam();
        BuildQueryParam.buildParam(entity, param);
        List<T> data = getMapper().list(param);
        if (data.isEmpty() || data.size() == 0) {
            data = new ArrayList<>();
        }
        return data;
    }

    @Override
    public List<T> list() throws Exception {
        List<T> data = getMapper().list();
        if (data.isEmpty() || data.size() == 0) {
            data = new ArrayList<>();
        }
        return data;
    }

    @Override
    public List<T> list(String id) throws Exception {
        List<T> data = getMapper().list(id);
        if (data.isEmpty() || data.size() == 0) {
            data = new ArrayList<>();
        }
        return data;
    }

    @Override
    public Map<String, Object> listPaging(T entity, String page, String pageSize, String startDate, String endDate, GenericQueryParam param) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (param == null) {
            param = new GenericQueryParam();
        }
        BuildQueryParam.buildParam(entity, param);
        if (StringUtils.isNotEmpty(startDate)) {
            startDate += START_DATE_FORMAT;
            param.fill(START_DATE, startDate);
        }
        if (StringUtils.isNotEmpty(endDate)) {
            endDate += END_DATE_FORMAT;
            param.fill(END_DATE, endDate);
        }
        int rowCount = count(param);
        result = CommonUtils.para4Page(result, CommonUtils.paraPage(page), CommonUtils.paraPageSize(pageSize), rowCount);
        if (rowCount > 0) {
            param.fill(BEGIN, result.get(BEGIN));
            param.fill(PAGE_SIZE, result.get(PAGE_SIZE));
            result.put(DATA, CommonUtils.dataNull(getMapper().listPaging(param)));
        } else {
            result.put(DATA, EMPTY_STRING);
        }
        return result;
    }

    @Override
    public int count(T entity) throws Exception {
        GenericQueryParam param = new GenericQueryParam();
        BuildQueryParam.buildParam(entity, param);
        return getMapper().count(param);
    }

    @Override
    public int count(GenericQueryParam param) throws Exception {
        return getMapper().count(param);
    }

}
