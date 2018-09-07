package com.victorzhang.recommendation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.recommendation.domain.Log;
import com.victorzhang.recommendation.service.LogService;
import com.victorzhang.recommendation.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.victorzhang.recommendation.util.Constants.USER_ID;

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

    @RequestMapping("listPaging.do")
    @ResponseBody
    public Map<String, Object> listPaging(String _page, String _pageSize, String logType, String startDate, String endDate) throws Exception {
        Log log = new Log();
        log.setLogType(logType);
        log.setUserId(CommonUtils.sesAttr(request, USER_ID));
        return logService.listPaging(log, _page, _pageSize, startDate, endDate, null);
    }
}
