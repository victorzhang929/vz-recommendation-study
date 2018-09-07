package com.victorzhang.recommendation.service.impl;

import com.victorzhang.recommendation.domain.Message;
import com.victorzhang.recommendation.mapper.BaseMapper;
import com.victorzhang.recommendation.mapper.MessageMapper;
import com.victorzhang.recommendation.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message, String> implements MessageService {

    @Autowired
    @Qualifier("messageMapper")
    private MessageMapper messageMapper;

    @Override
    protected BaseMapper<Message, String> getMapper() {
        return messageMapper;
    }

}
