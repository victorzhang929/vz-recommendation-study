package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.DownloadRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadRecordMapper extends BaseMapper<DownloadRecord, String> {
    int countByResourceDownload(DownloadRecord downloadRecord);
}
