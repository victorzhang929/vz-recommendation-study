package com.victorzhang.recommendation.domain;

import lombok.Data;

@Data
public class Resource {

    private String id;
    private String resourceName;
    private String resourceDescription;
    private String resourceTag;
    private String resourceType;
    private String resourceDownloadCount;
    private String resourceBrowseCount;
    private String resourceServerPath;
    private String userId;
    private String verifyType;
    private String gmtCreate;
    private String gmtModify;

    public Resource() {
    }

    public Resource(String id, String resourceName, String resourceDescription, String resourceTag, String resourceType, String resourceServerPath, String userId, String gmtCreate) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourceDescription = resourceDescription;
        this.resourceTag = resourceTag;
        this.resourceType = resourceType;
        this.resourceServerPath = resourceServerPath;
        this.userId = userId;
        this.gmtCreate = gmtCreate;
    }

    public Resource(String resourceName, String resourceType, String verifyType){
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.verifyType = verifyType;
    }

    public Resource(String id, String verifyType){
        this.id = id;
        this.verifyType = verifyType;
    }

    public Resource(String resourceTag){
        this.resourceTag = resourceTag;
    }

}
