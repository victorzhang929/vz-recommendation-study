package com.victorzhang.cfs.domain;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String commentContent;
    private String userId;
    private String resourceId;
    private String commentTime;

    public Comment(String userId) {
        this.userId = userId;
    }

    public Comment(String id, String commentContent) {
        this.id = id;
        this.commentContent = commentContent;
    }

    public Comment(String id, String commentContent, String userId, String resourceId, String commentTime){
        this.id = id;
        this.commentContent = commentContent;
        this.userId = userId;
        this.resourceId = resourceId;
        this.commentTime = commentTime;
    }
}
