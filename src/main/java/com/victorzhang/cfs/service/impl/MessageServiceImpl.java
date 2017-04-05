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

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message, String> implements MessageService {

    @Autowired
    @Qualifier("messageMapper")
    private MessageMapper messageMapper;

    @Override
    protected BaseMapper<Message, String> getMapper() {
        return messageMapper;
    }

    @Override
    public Map<String, Object> listUnreadMsg(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String receiveUserId = CommonUtils.sesAttr(request, "userId");
        map.put("unreadNum", messageMapper.countById(receiveUserId));
        map.put("data", CommonUtils.dataNull(messageMapper.listById(receiveUserId)));
        return map;
    }

    @Override
    public void doReadMsg(String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("readTime", CommonUtils.getDateTime());
        map.put("readIp", CommonUtils.getIpAddr());
        messageMapper.doReadMsg(map);
    }
}
