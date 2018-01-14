package com.zhang.colas.sns.web;

import com.zhang.colas.sns.entity.Attachment;
import com.zhang.colas.sns.entity.vo.QiniuUploadResult;
import com.zhang.colas.sns.service.AttachmentService;
import com.zhang.colas.sns.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author zxk
 * @date 2018-01-14 21:09:41
 */
@Controller
@RequestMapping("attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("imageUpload")
    public String uploadImage(MultipartFile file) {

        InputStream stream = null;
        try {
            stream = file.getInputStream();
            QiniuUploadResult result = QiniuUtils.upload(stream, file.getOriginalFilename());

            Attachment attachment = new Attachment();
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentPath(result.getPath());
            attachment.setAttachmentSize(file.getSize());
            attachment.setAttachmentState(1);
            attachment.setAttachmentSubffix("");
            attachment.setAttachmentType(1);
            attachment.setCreateBy(0);
            attachment.setCreateTime(new Date());

            Integer count = attachmentService.save(attachment);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }


}
