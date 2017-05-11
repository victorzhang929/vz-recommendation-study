package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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