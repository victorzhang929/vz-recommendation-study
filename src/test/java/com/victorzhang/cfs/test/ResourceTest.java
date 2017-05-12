package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.Score;
import com.victorzhang.cfs.domain.ScoreRecord;
import com.victorzhang.cfs.mapper.ScoreRecordMapper;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    @Qualifier("scoreRecordMapper")
    private ScoreRecordMapper scoreRecordMapper;

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
    public void testListPaging() throws Exception {
        Resource resource = new Resource();
        Map<String, Object> map = resourceService.listPaging(resource, "1", "1", "", "", null);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

    @Test
    public void testListNewestResource() throws Exception {
        List<Map<String, Object>> list = resourceService.listNewestResource();
        for (Map<String, Object> map : list) {
            for (Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }

    @Test
    public void testDownloadResource() throws Exception {
        ScoreRecord scoreRecord = new ScoreRecord("C4CA4238A0B923820DCC509A6F75849B", "65FD3294A01B4304B983FEF0FB86FB09", CommonUtils.getDateTime());
        ScoreRecord scoreRecordDB = scoreRecordMapper.get(scoreRecord);
        System.out.println(scoreRecordDB);
    }

    @Test
    public void testResourceBrowseCount() throws Exception {
        Resource resource = resourceService.getById("968F2C132B9641AF8B8CBD73367AF2D9");
        Resource updateResourceDownloadOrBrowseCount = new Resource();
        int resourceBrowseCount = Integer.parseInt(resource.getResourceBrowseCount()) + 1;
        updateResourceDownloadOrBrowseCount.setResourceDownloadCount(String.valueOf(resourceBrowseCount));

        updateResourceDownloadOrBrowseCount.setId("968F2C132B9641AF8B8CBD73367AF2D9");
        resourceService.update(updateResourceDownloadOrBrowseCount);
    }

    @Test
    public void testListResource() throws Exception {
        List<Resource> list = resourceService.list();
        System.out.println(list.toString());
    }

}
