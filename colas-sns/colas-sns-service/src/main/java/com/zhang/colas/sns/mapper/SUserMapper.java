package com.zhang.colas.sns.mapper;

import com.zhang.colas.sns.entity.SUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);

    List<SUser> getUserByGithubId(@Param("githubId") Integer githubId);
}