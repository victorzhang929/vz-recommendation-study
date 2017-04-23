package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class DownloadRecord {
    private String id;
    private String resourceId;
    private String downloadTime;
    private String userId;

    public DownloadRecord(String userId) {
        this.userId = userId;
    }
}
