package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.service.ResourceService;
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

import java.util.Map;

import static com.victorzhang.cfs.util.Constants.USER_ID;
import static com.victorzhang.cfs.util.Constants.UTF_8;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class ResourceTest {

    @Autowired
    @Qualifier("resourceService")
    private ResourceService resourceService;

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
    public void testListPaging() throws Exception{
        Resource resource = new Resource();
        Map<String, Object> map = resourceService.listPaging(resource, "1", "1", "", "", null);
        for(Map.Entry entry: map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }
}
