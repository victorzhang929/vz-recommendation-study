package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.service.LogService;
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

import static com.victorzhang.cfs.util.Constants.FIND_PASSWORD;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class LogTest {

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void before() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testSave() throws Exception {
        Log log = new Log();
        log.setId("B12FA30147A04EC78BA0CCBFDD17A083");
        log.setLogType(" 登录系统 ");
        log.setLogContent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
        log.setUserId("C4CA4238A0B923820DCC509A6F75849B");
        log.setUserDate("2017-04-02 15:20:13");
        log.setUserIp("127.0.0.1");
        logService.save(log);
    }

    @Test
    public void testSaveLogByLogTypeAndLogContent() throws Exception{
        request.getSession().setAttribute("userId","C4CA4238A0B923820DCC509A6F75849B");
        logService.saveLogByLogTypeAndLogContent(FIND_PASSWORD, "victorzhang0929@hotmail.com");
    }
}
