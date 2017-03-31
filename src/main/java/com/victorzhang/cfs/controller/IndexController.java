package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Index Page Controller
 * Created by victorzhang on 2017/3/31.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    @Qualifier("indexService")
    private IndexService indexService;

    @Autowired
    private HttpServletRequest request;
}
