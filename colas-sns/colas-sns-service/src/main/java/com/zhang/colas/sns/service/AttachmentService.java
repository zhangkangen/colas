package com.zhang.colas.sns.service;

import com.zhang.colas.sns.entity.Attachment;

/**
 * @author zxk
 * @date 2018-02-03 01:17:31
 */
public interface AttachmentService {
    Integer save(Attachment attachment);

    Attachment getById(Integer id);
}
