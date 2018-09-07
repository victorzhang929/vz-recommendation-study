package com.victorzhang.recommendation.mapper;

import com.victorzhang.recommendation.domain.DownloadRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadRecordMapper extends BaseMapper<DownloadRecord, String> {
    int countByResourceDownload(DownloadRecord downloadRecord);
}
