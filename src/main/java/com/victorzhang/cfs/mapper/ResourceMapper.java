package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.ResourceDownload;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMapper extends BaseMapper<Resource, String> {
    int saveResourceDownload(ResourceDownload resourceDownload) throws Exception;
}
