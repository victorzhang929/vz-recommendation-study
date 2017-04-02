package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<User, String>{
    User doLoginByUsernameAndPassword(String username, String password, HttpServletRequest request) throws Exception;
}
