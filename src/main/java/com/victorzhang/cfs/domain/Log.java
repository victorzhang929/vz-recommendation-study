package com.victorzhang.cfs.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Log implements Serializable {

    private String id;
    private String logType;
    private String logContent;
    private String userId;
    private String userDate;
    private String userIp;
}
