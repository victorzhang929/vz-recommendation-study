package com.victorzhang.cfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("browseRecord")
public class BrowseRecordController {

    @RequestMapping("/forwardResourceBrowseRecordUI")
    public String forwardResourceBrowseRecordUI(){
        return "resourceBrowseRecord";
    }
}
