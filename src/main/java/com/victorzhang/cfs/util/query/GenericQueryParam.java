package com.victorzhang.cfs.util.query;

import java.util.LinkedHashMap;

public class GenericQueryParam extends LinkedHashMap<String, Object> implements ListQueryParam {

    private static final long serialVersionUID = 1L;
    private static final String PAGE_KEY = "_page";
    private static final String PAGESIZE_KEY = "_pageSize";
    private static final String TOTALPAGE_KEY = "_totalPage";
    private static final String BEGIN_KEY = "_begin";
    private static final String END_KEY = "_end";

    public GenericQueryParam() {
    }

    public GenericQueryParam(Integer page, Integer pageSize) {
        setPage(page);
        setPageSize(pageSize);
    }

    @Override
    public QueryParam fill(String key, Object value) {
        put(key, value);
        return this;
    }

    @Override
    public Integer getPage() {
        return (Integer) get(PAGE_KEY);
    }

    @Override
    public Integer getPageSize() {
        return (Integer) get(PAGESIZE_KEY);
    }

    @Override
    public void setPage(Integer page) {
        put(PAGE_KEY, page);
    }

    @Override
    public void setPageSize(Integer pageSizze) {
        put(PAGESIZE_KEY, pageSizze);
    }

    @Override
    public Integer getBegin() {
        return (Integer) get(BEGIN_KEY);
    }

    @Override
    public void setBegin(Integer begin) {
        put(BEGIN_KEY, begin);
    }

    @Override
    public Integer getEnd() {
        return (Integer) get(END_KEY);
    }

    @Override
    public void setEnd(Integer end) {
        put(END_KEY, end);
    }

    @Override
    public Integer getTotalPage() {
        return (Integer) get(TOTALPAGE_KEY);
    }

    @Override
    public void setTotalPage(Integer totalPage) {
        put(TOTALPAGE_KEY, totalPage);
    }
}