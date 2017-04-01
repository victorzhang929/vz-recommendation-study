package com.victorzhang.cfs.test;

import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.service.LogService;
import com.victorzhang.cfs.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
public class LogTest {

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Test
    public void testSave(){
        Log log = new Log();
        log.setId(CommonUtils.newUuid());
        log.setLog_type("测试类型");
        log.setLog_content("测试内容");
        log.setUser_id("C4CA4238A0B923820DCC509A6F75849B");
        log.setUser_date("2017-03-30 18:45:49");
        log.setUser_ip("192.168.0.1");
        logService.save(log);
    }
}
