package com.zhang.colas.sns.service.impl;

import com.zhang.colas.common.PageParams;
import com.zhang.colas.common.PageResult;
import com.zhang.colas.sns.entity.Feed;
import com.zhang.colas.sns.mapper.FeedMapper;
import com.zhang.colas.sns.service.FeedService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author zxk
 * @date 2018-01-20 00:47:26
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedMapper feedMapper;


    @Override
    public PageResult queryList(PageParams pageParams) {

        PageResult pageResult = new PageResult();
        List<Feed> list = feedMapper.queryList(pageParams);
        if (list.size() > 0) {
            if (pageParams.getSinceId() > 0) {
                pageResult.setSinceId(list.get(0).getCreateTime().getTime());
                Collections.reverse(list);
            } else {
                pageResult.setMaxId(list.get(0).getCreateTime().getTime());
            }
        }
        pageResult.setData(list);
        pageResult.setSuccess(true);
        return pageResult;
    }
}
