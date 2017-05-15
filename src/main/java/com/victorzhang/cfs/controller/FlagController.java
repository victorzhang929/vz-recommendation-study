package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("flag")
public class FlagController {

    @Autowired
    @Qualifier("flagService")
    private FlagService flagService;

    @RequestMapping("/updateFlag.do")
    @ResponseBody
    public void updateFlag(String flag) throws Exception {
        flagService.updateFlag(flag);
    }
}
