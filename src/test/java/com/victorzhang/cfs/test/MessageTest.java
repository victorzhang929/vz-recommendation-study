package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Message;
import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.service.MessageService;
import com.victorzhang.cfs.service.UserService;
import com.victorzhang.cfs.util.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static com.victorzhang.cfs.util.Constants.USER_ID;
import static com.victorzhang.cfs.util.Constants.UTF_8;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class MessageTest {

    @Autowired
    @Qualifier("messageService")
    private MessageService messageService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void before() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding(UTF_8);
        response = new MockHttpServletResponse();
        request.getSession().setAttribute(USER_ID, "C4CA4238A0B923820DCC509A6F75849B");
    }

    @Test
    public void testCount() throws Exception {
        Message message = new Message();
        message.setReceiveUserId(CommonUtils.sesAttr(request, USER_ID));
        message.setIsRead("0");
        System.out.println(messageService.count(message));
    }

    @Test
    public void testList() throws Exception {
        Message message = new Message();
        message.setReceiveUserId(CommonUtils.sesAttr(request, USER_ID));
        message.setIsRead("0");
        System.out.println(messageService.list(message));
    }

    @Test
    public void testSave() throws Exception {
        List<User> users = userService.list();
        if (users.size() == 0){
            throw new Exception();
        }
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setMsgContent("消息内容");
        message.setSendUserId("5844279643990646205");
        message.setSendTime(CommonUtils.getDateTime());
        message.setSendUserIp("127.0.1");
        message.setIsRead("0");
        for (User user : users) {
            message.setId(CommonUtils.newUuid());
            message.setReceiveUserId(user.getId());
            messages.add(message);
        }
        messageService.save(messages);
    }

}
