package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.domain.Message;
import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.service.MessageService;
import com.victorzhang.cfs.service.UserService;
import com.victorzhang.cfs.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/listUnreadMsg.do")
    @ResponseBody
    public Map<String, Object> listUnreadMsg() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Message message = new Message(CommonUtils.sesAttr(request, USER_ID), UNREAD);
        map.put(COUNT, messageService.count(message));
        map.put(DATA, messageService.list(message));
        return map;
    }

    @RequestMapping("/doReadMsg.do")
    @ResponseBody
    public void doReadMsg(String id) throws Exception {
        Message message = new Message(id, CommonUtils.getDateTime(), CommonUtils.getIpAddr(), IS_READ);
        messageService.update(message);
    }

    @RequestMapping("/publicNotice.do")
    @ResponseBody
    public boolean publicNotice(String msgContent) throws Exception {
        List<User> users = userService.list();
        if (users.size() == 0) {
            throw new Exception();
        }
        List<Message> messages = new ArrayList<>();
        String sendUserId = CommonUtils.sesAttr(request, USER_ID);
        String sendTime = CommonUtils.getDateTime();
        String sendUserIp = CommonUtils.getIpAddr();
        for (User user : users) {
            Message message = new Message();
            message.setMsgContent(msgContent);
            message.setSendUserId(sendUserId);
            message.setSendTime(sendTime);
            message.setSendUserIp(sendUserIp);
            message.setIsRead(UNREAD);
            message.setId(CommonUtils.newUuid());
            message.setReceiveUserId(user.getId());
            messages.add(message);
        }
        return messageService.save(messages);
    }

    @RequestMapping("/forwardUserMessageUI.do")
    public String forwardAllMsgUI() throws Exception{
        return "userMessage";
    }

    @RequestMapping("/listUserMessage.do")
    @ResponseBody
    public Map<String, Object> listUserMessage(String _page, String _pageSize, String isRead, String startDate, String endDate) throws Exception{
        Message message = new Message(CommonUtils.sesAttr(request,USER_ID),isRead);
        return messageService.listPaging(message, _page, _pageSize, startDate, endDate, null);
    }

}
