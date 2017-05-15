package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Resource;
import com.victorzhang.cfs.domain.ScoreRecord;
import com.victorzhang.cfs.mapper.ScoreRecordMapper;
import com.victorzhang.cfs.service.RecommendationService;
import com.victorzhang.cfs.service.ResourceService;
import com.victorzhang.cfs.service.ScoreRecordService;
import com.victorzhang.cfs.util.CommonUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
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
    @Qualifier("recommendationService")
    private RecommendationService recommendationService;

    @Autowired
    @Qualifier("scoreRecordService")
    private ScoreRecordService scoreRecordService;

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

    @Test
    public void testUserBaseRecommendation() throws TasteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-common.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        /*MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDatabaseName("cfs");*/
        JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "resource_score", "user_id", "resource_id", "rating", "rating_time");

        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(4, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(5844279643990646201l, 2);
        for (RecommendedItem recommendation : recommendations) {
            //输出推荐结果
            System.out.println(recommendation);
        }

    }

    @Test
    public void testListVerifyResource() throws Exception {
        Resource resource = new Resource();
        System.out.println(resourceService.listPaging(resource, "0", "10", null, null, null));
    }

    @Test
    public void testItemBasedRecommendation() throws Exception {
        System.out.println(recommendationService.listItemBasedRecommendationResource(5844279643990646201L));
    }

    @Test
    public void testGetById() throws Exception {
        Resource resource = resourceService.getById("4844279643990646204");
        System.out.println(resource);
    }

    @Test
    public void testSaveScoreRecord() throws Exception {
        ScoreRecord scoreRecord = new ScoreRecord();
        scoreRecord.setUserId("5844279643990646201");
        scoreRecord.setResourceId("313414239140184064");
        scoreRecord.setRatingTime("2017-05-15 16:40:04");
        scoreRecord.setRating("3");
        scoreRecord.setScoreFlag("0");
        boolean flag = scoreRecordService.update(scoreRecord);
        System.out.println(flag);
    }

}
