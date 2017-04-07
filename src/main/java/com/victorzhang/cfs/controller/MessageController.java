package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.Message;
import com.victorzhang.cfs.service.MessageService;
import com.victorzhang.cfs.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;

@Controller
@RequestMapping("message")
public class MessageController {

    private static final String IS_READ = "1";
    private static final String UNREAD = "0";


    @Autowired
    @Qualifier("messageService")
    private MessageService messageService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/listUnreadMsg.do")
    @ResponseBody
    public Map<String, Object> listUnreadMsg() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Message message = new Message();
        message.setReceiveUserId(CommonUtils.sesAttr(request, USER_ID));
        message.setIsRead(UNREAD);
        map.put(COUNT, messageService.count(message));
        map.put(DATA, messageService.list(message));
        return map;
    }

    @RequestMapping("/doReadMsg.do")
    @ResponseBody
    public void doReadMsg(String id) throws Exception {
        Message message = new Message();
        message.setId(id);
        message.setReadTime(CommonUtils.getDateTime());
        message.setReadIp(CommonUtils.getIpAddr());
        message.setIsRead(IS_READ);
        messageService.update(message);
    }
}
