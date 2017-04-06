package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("forwardUserLogUI.do")
    public String forwardUserLogUI() {
        return "userLog";
    }

    @RequestMapping("forwardSystemLogUI.do")
    public String forwardSystemLogUI() {
        return "systemLog";
    }

    @RequestMapping("listLogType.do")
    @ResponseBody
    public List<Map<String, Object>> listLogType() throws Exception {
        return logService.listLogType(request);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public List<Map<String, Object>> list(String _page,String _pageSize,String logType,String startDate,String endDate) throws Exception {
        return logService.list(request);
    }
}
