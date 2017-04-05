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
    private String roleId;
    private String username;
    private String password;
    private String realname;
    private String userIdcard;
    private String userMobile;
    private String userEmail;
    private String randomCode;
    //false--male and true--female
    private Boolean gender;
    private String tag;
    private String gmtCreate;
    private String gmtModify;
}
