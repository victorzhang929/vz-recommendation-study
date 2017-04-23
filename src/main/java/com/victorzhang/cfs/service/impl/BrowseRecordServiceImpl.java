package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.BrowseRecord;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.BrowseRecordMapper;
import com.victorzhang.cfs.service.BrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("browseRecordService")
public class BrowseRecordServiceImpl extends BaseServiceImpl<BrowseRecord, String> implements BrowseRecordService {

    @Autowired
    @Qualifier("browseRecordMapper")
    private BrowseRecordMapper browseRecordMapper;

    @Override
    protected BaseMapper<BrowseRecord, String> getMapper() {
        return browseRecordMapper;
    }
}
