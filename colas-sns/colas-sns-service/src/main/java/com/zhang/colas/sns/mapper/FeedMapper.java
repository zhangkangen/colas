package com.zhang.colas.sns.mapper;

import com.zhang.colas.common.PageParams;
import com.zhang.colas.sns.entity.Feed;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feed record);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);

    List<Feed> queryList(PageParams pageParams);
}