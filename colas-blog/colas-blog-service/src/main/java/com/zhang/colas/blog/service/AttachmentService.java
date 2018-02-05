package com.zhang.colas.blog.service;


import com.zhang.colas.blog.entity.Attachment;

public interface AttachmentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);

    Attachment getByMd5(String md5);
}
