package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class ScoreRecord {
    private String userId;
    private String resourceId;
    private String rating;
    private String ratingTime;
    private String scoreFlag;

    public ScoreRecord(){}

    public ScoreRecord(String userId, String resourceId, String rating, String ratingTime, String scoreFlag){
        this.userId = userId;
        this.resourceId = resourceId;
        this.rating = rating;
        this.ratingTime = ratingTime;
        this.scoreFlag = scoreFlag;
    }

    public ScoreRecord(String userId, String resourceId,String ratingTime){
        this.userId = userId;
        this.resourceId = resourceId;
        this.ratingTime = ratingTime;
    }
}
