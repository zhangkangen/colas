package com.zhang.colas.sns.service.impl;

import com.zhang.colas.sns.entity.Attachment;
import com.zhang.colas.sns.mapper.AttachmentMapper;
import com.zhang.colas.sns.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public Integer save(Attachment attachment) {
        return attachmentMapper.insert(attachment);
    }
}
