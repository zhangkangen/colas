package com.zhang.colas.blog.service.impl;

import com.zhang.colas.blog.entity.Attachment;
import com.zhang.colas.blog.repository.AttachmentRepository;
import com.zhang.colas.blog.service.AttachmentService;
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
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment getByMd5(String md5) {
        List<Attachment> list =attachmentRepository.selectByMd5(md5);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}
