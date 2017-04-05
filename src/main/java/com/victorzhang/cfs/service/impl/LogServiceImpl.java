package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.LogMapper;
import com.victorzhang.cfs.service.LogService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

}
