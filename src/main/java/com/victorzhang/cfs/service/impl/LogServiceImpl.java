package com.victorzhang.cfs.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.LogMapper;
import com.victorzhang.cfs.service.LogService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log, String> implements LogService {

    @Autowired
    @Qualifier("logMapper")
    private LogMapper logMapper;

    @Override
    protected BaseMapper<Log, String> getMapper() {
        return logMapper;
    }

    public boolean saveLogByLogTypeAndLogContent(String logType, String logContent, String userId) throws Exception {
        Log log = new Log();

        log.setId(CommonUtils.newUuid());
        log.setLogType(logType);
        log.setLogContent(logContent);
        log.setUserId(userId);
        log.setUserDate(CommonUtils.getDateTime());
        log.setUserIp(CommonUtils.getIpAddr());

        return super.save(log);
    }

    @Override
    public boolean saveLogByLogTypeAndLogContent(String logType, String logContent) throws Exception {
        String userId = (String) CommonUtils.getSession(false).getAttribute("userId");
        return saveLogByLogTypeAndLogContent(logType, logContent, userId);
    }

    @Override
    public List<Map<String, Object>> listLogType(HttpServletRequest request) throws Exception {
        String userId = null;
        //if user's role is admin, search all log type
        if (!StringUtils.equals(CommonUtils.sesAttr(request, "roleId"), "DEDD7D0EDED9445083518A35EC5940AB")) {
            userId = CommonUtils.sesAttr(request, "userId");
        }
        return logMapper.listLogType(userId);
    }

}
