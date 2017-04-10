package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.service.UserService;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class UserTest {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void before() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testGetUserByUsernameAndPassword() throws Exception {
        userService.doLoginByUsernameAndPassword("admin", "1", request);
    }

    @Test
    public void testDoGetUserByEmail() throws Exception {
        String email = "victorzhang0929@hotmail.com";
        userService.doGetUserByEmail(email);
    }

    @Test
    public void testList() throws Exception {
        List<User> users = userService.list();
        for (User user : users) {
            System.out.println(user.getId());
        }
    }
}
