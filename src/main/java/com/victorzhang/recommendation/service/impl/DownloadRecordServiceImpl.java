package com.victorzhang.recommendation.service.impl;

import com.victorzhang.recommendation.domain.DownloadRecord;
import com.victorzhang.recommendation.mapper.BaseMapper;
import com.victorzhang.recommendation.mapper.DownloadRecordMapper;
import com.victorzhang.recommendation.service.DownloadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("downloadRecordService")
public class DownloadRecordServiceImpl extends BaseServiceImpl<DownloadRecord,String> implements DownloadRecordService {

    @Autowired
    @Qualifier("downloadRecordMapper")
    private DownloadRecordMapper downloadRecordMapper;

    @Override
    protected BaseMapper<DownloadRecord, String> getMapper() {
        return downloadRecordMapper;
    }
}
