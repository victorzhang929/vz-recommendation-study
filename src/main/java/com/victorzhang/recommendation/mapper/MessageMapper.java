package com.victorzhang.recommendation.mapper;

import com.victorzhang.recommendation.domain.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends BaseMapper<Message, String> {
}
