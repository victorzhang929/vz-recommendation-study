package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.DownloadRecord;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.DownloadRecordMapper;
import com.victorzhang.cfs.service.DownloadRecordService;
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
