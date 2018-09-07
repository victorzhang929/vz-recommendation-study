package com.victorzhang.recommendation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.recommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("manage")
public class ManageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/forwardUserManagementUI")
    public String forwardUserManagementUI() {
        return "userManagement";
    }

    @RequestMapping("/listUserType.do")
    @ResponseBody
    public List<Map<String, Object>> listUserType() throws Exception {
        return userService.listUserType();
    }

    @RequestMapping("/forwardResourceManagementUI")
    public String forwardResourceManagementUI() {
        return "resourceManagement";
    }
}