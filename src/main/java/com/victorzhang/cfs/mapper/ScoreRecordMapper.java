package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.ScoreRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecordMapper extends BaseMapper<ScoreRecord, String> {
    List<String> listScoreRecord(String userId) throws Exception;
}