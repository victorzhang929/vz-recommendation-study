package com.victorzhang.recommendation.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String id;
    private String msgContent;
    private String receiveUserId;
    private String sendUserId;
    private String sendTime;
    private String sendUserIp;
    private String isRead;
    private String readTime;
    private String readIp;

    public Message() {
    }

    public Message(String receiveUserId, String isRead) {
        this.receiveUserId = receiveUserId;
        this.isRead = isRead;
    }

    public Message(String id, String readTime, String readIp, String isRead) {
        this.id = id;
        this.readTime = readTime;
        this.readIp = readIp;
        this.isRead = isRead;
    }


}
