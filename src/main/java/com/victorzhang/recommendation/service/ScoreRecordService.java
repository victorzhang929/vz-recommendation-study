package com.victorzhang.recommendation.service;

import com.victorzhang.recommendation.domain.ScoreRecord;

import java.util.List;

public interface ScoreRecordService extends BaseService<ScoreRecord, String> {
    List<String> listScoreRecord(String userId) throws Exception;
}
