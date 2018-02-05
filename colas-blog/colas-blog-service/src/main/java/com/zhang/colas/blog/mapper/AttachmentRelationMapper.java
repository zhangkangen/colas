package com.zhang.colas.blog.mapper;

import com.zhang.colas.blog.entity.AttachmentRelation;

public interface AttachmentRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttachmentRelation record);

    int insertSelective(AttachmentRelation record);

    AttachmentRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttachmentRelation record);

    int updateByPrimaryKey(AttachmentRelation record);
}