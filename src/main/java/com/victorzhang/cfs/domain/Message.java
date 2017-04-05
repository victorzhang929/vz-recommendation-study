package com.victorzhang.cfs.domain;

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
}
