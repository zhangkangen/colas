package com.zhang.colas.sns.mapper;

import com.zhang.colas.sns.entity.Feed;

public interface FeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feed record);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);
}