package com.victorzhang.cfs.controller;

import com.victorzhang.cfs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    @Qualifier("messageService")
    private MessageService messageService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/listUnreadMsg.do")
    @ResponseBody
    public Map<String, Object> listUnreadMsg() throws Exception{
        return messageService.listUnreadMsg(request);
    }

    @RequestMapping("/doReadMsg.do")
    @ResponseBody
    public void doReadMsg(String id) throws Exception {
        messageService.doReadMsg(id);
    }
}
