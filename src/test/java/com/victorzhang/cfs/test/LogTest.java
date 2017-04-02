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
        log.setLog_type(" 登录系统 ");
        log.setLog_content("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
        log.setUser_id("C4CA4238A0B923820DCC509A6F75849B");
        log.setUser_date("2017-04-02 15:20:13");
        log.setUser_ip("127.0.0.1");
        logService.save(log);
    }
}
