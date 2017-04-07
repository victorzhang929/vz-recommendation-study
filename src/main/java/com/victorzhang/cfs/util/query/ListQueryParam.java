package com.victorzhang.cfs.util.query;

public interface ListQueryParam extends QueryParam {

    Integer getPage();

    Integer getPageSize();

    void setPage(Integer page);

    void setPageSize(Integer pageSize);

    Integer getBegin();

    void setBegin(Integer begin);

    Integer getEnd();

    void setEnd(Integer end);

    Integer getTotalPage();

    void setTotalPage(Integer totalPage);
}
