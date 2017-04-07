package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Message;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.MessageMapper;
import com.victorzhang.cfs.service.MessageService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.COUNT;
import static com.victorzhang.cfs.util.Constants.DATA;
import static com.victorzhang.cfs.util.Constants.USER_ID;

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
