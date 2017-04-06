package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<User, String> {
    User doLoginByUsernameAndPassword(String username, String password, HttpServletRequest request) throws Exception;

    String doGetUserByEmail(String email) throws Exception;

    String doResetPassword(String username, String checkCode, String password, String rePassword) throws Exception;

    String doResetPassword(String oldPassword, String password, String rePassword, HttpServletRequest request) throws Exception;

    void doExit(HttpServletRequest request) throws Exception;
}
