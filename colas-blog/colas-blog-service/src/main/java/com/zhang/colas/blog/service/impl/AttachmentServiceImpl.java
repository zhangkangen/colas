package com.zhang.colas.blog.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhang.colas.blog.entity.Attachment;
import com.zhang.colas.blog.repository.AttachmentRepository;
import com.zhang.colas.blog.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zxk
 * @date 2018-02-05 22:47:00
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
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
