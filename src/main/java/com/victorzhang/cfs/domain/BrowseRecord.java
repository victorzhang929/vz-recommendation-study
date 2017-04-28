package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class BrowseRecord {
    private String id;
    private String resourceId;
    private String browseTime;
    private String userId;

    public BrowseRecord(String userId){
        this.userId = userId;
    }
}
