package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResourceMapper extends BaseMapper<Resource, String> {
    List<Map<String, Object>> listNewestResource() throws Exception;

    List<Map<String, Object>> listHotResource(int count) throws Exception;
}
