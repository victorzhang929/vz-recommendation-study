package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.ScoreRecord;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.ScoreRecordMapper;
import com.victorzhang.cfs.service.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("scoreRecordService")
public class ScoreRecordServiceImpl extends BaseServiceImpl<ScoreRecord, String> implements ScoreRecordService {

    @Autowired
    @Qualifier("scoreRecordMapper")
    private ScoreRecordMapper scoreRecordMapper;

    @Override
    protected BaseMapper<ScoreRecord, String> getMapper() {
        return scoreRecordMapper;
    }
}
