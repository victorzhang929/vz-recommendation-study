package com.victorzhang.cfs.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Log implements Serializable {

    private String id;
    private String log_type;
    private String log_content;
    private String user_id;
    private String user_date;
    private String user_ip;
}
