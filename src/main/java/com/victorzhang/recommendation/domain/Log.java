package com.victorzhang.recommendation.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Log implements Serializable {

    private String id;
    private String logType;
    private String logContent;
    private String userId;
    private String userDate;
    private String userIp;
}
