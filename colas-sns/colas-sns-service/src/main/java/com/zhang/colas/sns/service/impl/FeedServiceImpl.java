package com.zhang.colas.sns.service.impl;

import com.zhang.colas.sns.mapper.FeedMapper;
import com.zhang.colas.sns.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxk
 * @date 2018-01-20 00:47:26
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedMapper feedMapper;


}
