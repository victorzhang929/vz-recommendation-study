package com.victorzhang.recommendation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.recommendation.domain.BrowseRecord;
import com.victorzhang.recommendation.service.BrowseRecordService;
import com.victorzhang.recommendation.util.CommonUtils;
import com.victorzhang.recommendation.util.query.GenericQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.victorzhang.recommendation.util.Constants.USER_ID;

@Controller
@RequestMapping("browseRecord")
public class BrowseRecordController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("browseRecordService")
    private BrowseRecordService browseRecordService;

    @RequestMapping("/forwardResourceBrowseRecordUI.do")
    public String forwardResourceBrowseRecordUI() {
        return "resourceBrowseRecord";
    }

    @RequestMapping("/listPaging.do")
    @ResponseBody
    public Map<String, Object> listPaging(String _page, String _pageSize, String resourceName, String resourceType, String startDate, String endDate) throws Exception {
        BrowseRecord browseRecord = new BrowseRecord(CommonUtils.sesAttr(request, USER_ID));
        GenericQueryParam param = new GenericQueryParam();
        param.fill("resourceName", resourceName);
        param.fill("resourceType", resourceType);
        return browseRecordService.listPaging(browseRecord, _page, _pageSize, startDate, endDate, param);
    }
}
