package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.LogMapper;
import com.victorzhang.cfs.service.LogService;
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

}
