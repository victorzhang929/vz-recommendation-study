package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Index Page Controller
 * Created by victorzhang on 2017/3/31.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login.index")
    @ResponseBody
    public User login(String username, String password) throws Exception {
        return  userService.doLoginByUsernameAndPassword(username, password, request);
    }

    @RequestMapping("/forwardMainPage.do")
    public String forwardMainPage(){
        return "main";
    }
}
