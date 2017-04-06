package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LogMapper extends BaseMapper<Log, String> {

    List<Map<String, Object>> listLogType(@Param("userId") String userId) throws Exception;
}
