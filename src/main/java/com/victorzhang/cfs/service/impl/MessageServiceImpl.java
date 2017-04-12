package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Message;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.MessageMapper;
import com.victorzhang.cfs.service.MessageService;
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
