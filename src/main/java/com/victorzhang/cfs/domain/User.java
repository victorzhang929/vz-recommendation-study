package com.victorzhang.cfs.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Definite User Model
 * Created by victorzhang on 2017/3/31.
 */
@Data
public class User implements Serializable {

    private String id;
    private String role_id;
    private String username;
    private String password;
    private String realname;
    private String user_idcard;
    private String user_mobile;
    private String user_email;
    private String random_code;
    //false--male and true--female
    private Boolean gender;
    private String tag;
    private String gmt_create;
    private String gmt_modify;
}
