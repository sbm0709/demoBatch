package com.demobatch;

import com.demobatch.config.ScheduleConfig;
import com.demobatch.schedule.MyJob;
import com.demobatch.service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoBatchApplicationTests {

    @Autowired
    MyJob job;

    @Autowired
    ScheduleConfig scheduleConfig;

    @Autowired
    MainService mainService;

    @Test
    void contextLoads() throws IOException {
        job.shopViewCountToCsv();
    }

}
