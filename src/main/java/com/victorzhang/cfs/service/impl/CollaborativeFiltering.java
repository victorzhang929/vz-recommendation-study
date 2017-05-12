package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.ScoreRecord;
import com.victorzhang.cfs.mapper.ScoreRecordMapper;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.service.ScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 核心算法——协同过滤算法
 */
public class CollaborativeFiltering {

    @Autowired
    @Qualifier("scoreRecordService")
    private ScoreRecordService scoreRecordService;

    /**
     * 基于项目的协同过滤算法
     */
    //TODO
    private List<Map<String, Object>> listResourceNoScore(String userId) throws Exception {
        List<String> scoreRecords = scoreRecordService.listScoreRecord(null);
        List<String> scoredRecords = scoreRecordService.listScoreRecord(userId);
        List<String> noScoredRecords = new ArrayList<>();

        for (String resourceId : scoreRecords) {
            if (!scoredRecords.contains(resourceId)) {
                noScoredRecords.add(resourceId);
            }
        }
        return null;
    }

}
