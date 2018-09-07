package com.victorzhang.recommendation.mapper;

import java.util.List;
import java.util.Map;

import com.victorzhang.recommendation.domain.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import static com.victorzhang.recommendation.util.Constants.USER_ID;

@Repository
public interface LogMapper extends BaseMapper<Log, String> {

    List<Map<String, Object>> listLogType(@Param(USER_ID) String userId) throws Exception;
}
