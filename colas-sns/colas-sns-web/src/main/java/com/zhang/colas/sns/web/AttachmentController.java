package com.zhang.colas.sns.web;

import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.core.entity.QiniuUploadResult;
import com.zhang.colas.core.utils.QiniuUtils;
import com.zhang.colas.sns.entity.Attachment;
import com.zhang.colas.sns.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

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
            attachment.setAttachmentSuffix("");
            //todo 附件应该 使用枚举
            attachment.setAttachmentType(1);
            attachment.setCreateBy(0);
            attachment.setCreateTime(new Date());

            Integer count = attachmentService.save(attachment);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }

    @GetMapping("getById")
    @ResponseBody
    public SimpleResult getById(Integer id) {
        Attachment attachment = attachmentService.getById(id);
        return SimpleResult.responseOk(attachment, "ok");
    }
}
