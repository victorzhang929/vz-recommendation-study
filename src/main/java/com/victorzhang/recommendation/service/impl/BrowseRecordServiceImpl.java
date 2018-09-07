package com.victorzhang.recommendation.service.impl;

import com.victorzhang.recommendation.domain.BrowseRecord;
import com.victorzhang.recommendation.mapper.BaseMapper;
import com.victorzhang.recommendation.mapper.BrowseRecordMapper;
import com.victorzhang.recommendation.service.BrowseRecordService;
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
