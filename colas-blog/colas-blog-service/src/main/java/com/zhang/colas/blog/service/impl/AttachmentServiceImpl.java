package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.api.service.AttachmentService;
import com.zhang.colas.blog.entity.Attachment;
import com.zhang.colas.blog.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxk
 * @date 2018-02-05 22:47:00
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return attachmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Attachment record) {
        return attachmentMapper.insert(record);
    }

    @Override
    public int insertSelective(Attachment record) {
        return attachmentMapper.insertSelective(record);
    }

    @Override
    public Attachment selectByPrimaryKey(Integer id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Attachment record) {
        return attachmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Attachment record) {
        return attachmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public Attachment getByMd5(String md5) {
        List<Attachment> list =attachmentMapper.selectByMd5(md5);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
