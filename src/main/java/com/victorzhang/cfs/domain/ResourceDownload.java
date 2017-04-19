package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class ResourceDownload {
    private String id;
    private String resourceId;
    private String downloadTime;
    private String userId;

    public ResourceDownload(){}

    public ResourceDownload(String id, String resourceId, String downloadTime, String userId){
        this.id = id;
        this.resourceId = resourceId;
        this.downloadTime = downloadTime;
        this.userId = userId;
    }
}
