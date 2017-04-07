package com.victorzhang.cfs.mapper;

import com.victorzhang.cfs.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MessageMapper extends BaseMapper<Message, String> {
}
