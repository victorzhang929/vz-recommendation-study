package com.victorzhang.recommendation.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FlagMapper {
    int updateFlag(String flag) throws Exception;

    Map<String, Object> getFlagValue() throws Exception;
}
