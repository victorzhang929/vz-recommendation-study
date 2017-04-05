package com.victorzhang.cfs.service;

import com.victorzhang.cfs.domain.Log;

public interface LogService extends BaseService<Log, String> {
    boolean saveLogByLogTypeAndLogContent(String logType, String logContent, String userId) throws Exception;

    boolean saveLogByLogTypeAndLogContent(String logType, String logContent) throws Exception;
}
