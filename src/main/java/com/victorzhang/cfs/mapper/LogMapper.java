package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.USER_ID;

@Repository
public interface LogMapper extends BaseMapper<Log, String> {

    List<Map<String, Object>> listLogType(@Param(USER_ID) String userId) throws Exception;
}
