package com.victorzhang.recommendation.mapper;

import com.victorzhang.recommendation.domain.BrowseRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface BrowseRecordMapper extends BaseMapper<BrowseRecord, String> {
    int countByResourceBrowse(BrowseRecord browseRecord) throws Exception;
}
