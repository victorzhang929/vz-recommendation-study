package com.victorzhang.recommendation.mapper;

import java.util.List;

import com.victorzhang.recommendation.domain.ScoreRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRecordMapper extends BaseMapper<ScoreRecord, String> {
    List<String> listScoreRecord(String userId) throws Exception;
}