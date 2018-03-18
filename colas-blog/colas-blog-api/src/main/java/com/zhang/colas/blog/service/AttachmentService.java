package com.zhang.colas.blog.service;


import com.zhang.colas.blog.entity.Attachment;

public interface AttachmentService {

    Attachment getByMd5(String md5);

    Attachment save(Attachment attachment);
}
