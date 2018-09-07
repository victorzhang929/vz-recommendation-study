package com.victorzhang.recommendation.service;

import com.victorzhang.recommendation.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User, String> {
    User doLoginByUsernameAndPassword(String username, String password, HttpServletRequest request) throws Exception;

    String doGetUserByEmail(String email) throws Exception;

    String doResetPassword(String username, String checkCode, String password, String rePassword) throws Exception;

    String doResetPassword(String oldPassword, String password, String rePassword, HttpServletRequest request) throws Exception;

    void doExit(HttpServletRequest request) throws Exception;

    Map<String,Object> getByUserInfo(String userId) throws Exception;

    List<Map<String,Object>> listUserType() throws Exception;
}
