package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<User, String>{
    User getUserByUsernameAndPassword(String username, String password, HttpServletRequest request);
}
