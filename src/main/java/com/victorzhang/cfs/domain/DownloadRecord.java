package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class DownloadRecord {
    private String resourceId;
    private String downloadTime;
    private String userId;

    public DownloadRecord(String userId) {
        this.userId = userId;
    }

    public DownloadRecord(String resourceId, String downloadTime, String userId){
        this.resourceId = resourceId;
        this.downloadTime = downloadTime;
        this.userId = userId;
    }
}
