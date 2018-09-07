package com.victorzhang.recommendation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.recommendation.domain.Message;
import com.victorzhang.recommendation.domain.User;
import com.victorzhang.recommendation.service.MessageService;
import com.victorzhang.recommendation.service.UserService;
import com.victorzhang.recommendation.util.CommonUtils;
import com.victorzhang.recommendation.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.victorzhang.recommendation.util.Constants.COUNT;
import static com.victorzhang.recommendation.util.Constants.DATA;
import static com.victorzhang.recommendation.util.Constants.USER_ID;

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
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1L,1L);
        for (User user : users) {
            Message message = new Message();
            message.setId(String.valueOf(idWorker.nextId()));
            message.setMsgContent(msgContent);
            message.setSendUserId(sendUserId);
            message.setSendTime(sendTime);
            message.setSendUserIp(sendUserIp);
            message.setIsRead(UNREAD);
            message.setReceiveUserId(user.getId());
            messages.add(message);
        }
        return messageService.save(messages);
    }

    @RequestMapping("/forwardUserMessageUI.do")
    public String forwardUserMessageUI() throws Exception{
        return "userMessage";
    }

    @RequestMapping("/listUserMessage.do")
    @ResponseBody
    public Map<String, Object> listUserMessage(String _page, String _pageSize, String isRead, String startDate, String endDate) throws Exception{
        Message message = new Message(CommonUtils.sesAttr(request,USER_ID),isRead);
        return messageService.listPaging(message, _page, _pageSize, startDate, endDate, null);
    }

}
