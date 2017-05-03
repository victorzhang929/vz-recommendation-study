package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.ResourceDownload;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResourceMapper extends BaseMapper<Resource, String> {
    int saveResourceDownload(ResourceDownload resourceDownload) throws Exception;

    List<Map<String, Object>> listNewestResource() throws Exception;

    List<Map<String, Object>> listHotResource() throws Exception;
}
