package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.BrowseRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowseRecordMapper extends BaseMapper<BrowseRecord, String> {
    int countByResourceBrowse(BrowseRecord browseRecord) throws Exception;
}
