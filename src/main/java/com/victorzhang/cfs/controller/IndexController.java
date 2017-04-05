package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
        return userService.doLoginByUsernameAndPassword(username, password, request);
    }

    @RequestMapping("/forwardMainUI.do")
    public String forwardMainUI() throws Exception {
        return "main";
    }

    @RequestMapping(value = "/sendEmail.index", produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String sendEmail(String email) throws Exception {
        return userService.doGetUserByEmail(email);
    }

    @RequestMapping("/forwardGraphicLinkUI.do")
    public String forwardMainPage() throws Exception {
        return "graphicLink";
    }

    @RequestMapping("/forwardResetPasswordUI.index")
    public String forwardResetPasswordUI() throws Exception {
        return "resetPassword";
    }

    @RequestMapping(value = "/doResetPassword.index", produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String doResetPassword(String username, String checkCode, String password, String rePassword) throws Exception{
        return userService.doResetPassword(username,checkCode,password,rePassword);
    }
}
