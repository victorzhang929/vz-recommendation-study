package com.victorzhang.recommendation.service.impl;

import com.victorzhang.recommendation.domain.ScoreRecord;
import com.victorzhang.recommendation.mapper.BaseMapper;
import com.victorzhang.recommendation.mapper.ScoreRecordMapper;
import com.victorzhang.recommendation.service.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scoreRecordService")
public class ScoreRecordServiceImpl extends BaseServiceImpl<ScoreRecord, String> implements ScoreRecordService {

    @Autowired
    @Qualifier("scoreRecordMapper")
    private ScoreRecordMapper scoreRecordMapper;

    @Override
    protected BaseMapper<ScoreRecord, String> getMapper() {
        return scoreRecordMapper;
    }

    @Override
    public List<String> listScoreRecord(String userId) throws Exception {
        return scoreRecordMapper.listScoreRecord(userId);
    }
}
