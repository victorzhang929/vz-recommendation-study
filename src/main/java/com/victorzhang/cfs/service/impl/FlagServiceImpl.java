package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.mapper.FlagMapper;
import com.victorzhang.cfs.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.UPDATE_ERROR;

@Service("flagService")
public class FlagServiceImpl implements FlagService {

    @Autowired
    @Qualifier("flagMapper")
    private FlagMapper flagMapper;

    @Override
    public void updateFlag(String flag) throws Exception {
        int successCount = flagMapper.updateFlag(flag);
        if (successCount <= 0) {
            throw new SQLException(UPDATE_ERROR);
        }
    }

    @Override
    public Map<String, Object> getFlagValue() throws Exception {
        return flagMapper.getFlagValue();
    }
}
