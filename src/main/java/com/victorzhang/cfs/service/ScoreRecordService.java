package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.ScoreRecord;

import java.util.List;

public interface ScoreRecordService extends BaseService<ScoreRecord, String> {
    List<String> listScoreRecord(String userId) throws Exception;
}
