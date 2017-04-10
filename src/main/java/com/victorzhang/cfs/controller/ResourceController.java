package com.victorzhang.cfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resource")
public class ResourceController {

    @RequestMapping("/forwardUserResourceUI.do")
    public String forwardUserResourceUI(){
        return "userResource";
    }
}
