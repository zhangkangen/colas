package com.zhang.colas.sns.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedServiceTest {

    private Logger logger = LoggerFactory.getLogger(FeedServiceTest.class);

    @Autowired
    private FeedService feedService;

    @Test
    public void testInsert() {
        try {
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
