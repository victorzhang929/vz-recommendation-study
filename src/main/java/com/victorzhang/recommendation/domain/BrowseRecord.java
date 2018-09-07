package com.victorzhang.recommendation.domain;

import lombok.Data;

@Data
public class BrowseRecord {
    private String resourceId;
    private String browseTime;
    private String userId;

    public BrowseRecord(String userId) {
        this.userId = userId;
    }

    public BrowseRecord(String resourceId, String browseTime, String userId) {
        this.resourceId = resourceId;
        this.browseTime = browseTime;
        this.userId = userId;
    }
}
