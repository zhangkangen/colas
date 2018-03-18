package com.zhang.colas.blog.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.colas.blog.entity.Attachment;
import com.zhang.colas.blog.enums.AttachmentTypeEnum;
import com.zhang.colas.blog.service.AttachmentService;
import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import com.zhang.colas.core.entity.QiniuUploadResult;
import com.zhang.colas.core.utils.QiniuUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.weaver.bcel.AtAjAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @Reference(
            version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345"
    )
    private AttachmentService attachmentService;

    @PostMapping("uploadImg")
    @ResponseBody
    public SimpleResult uploadImg(HttpServletRequest request, List<MultipartFile> files) {

        List<Attachment> list = new ArrayList<>();
        Iterator<MultipartFile> iterator = files.iterator();
        while (iterator.hasNext()) {
            MultipartFile file = iterator.next();
            LOGGER.info(file.getOriginalFilename());
            try {

                String md5 = DigestUtils.md5Hex(file.getBytes());
                LOGGER.info(md5);

                Attachment attachment = attachmentService.getByMd5(md5);
                if (null == attachment) {
                    QiniuUploadResult upload = QiniuUtils.upload(file.getInputStream(), file.getOriginalFilename());
                    LOGGER.info(upload.getUrl());

                    attachment = new Attachment();
                    attachment.setName(file.getOriginalFilename());
                    attachment.setPath(upload.getPath());
                    attachment.setAttachmentSize(file.getSize());
                    attachment.setAttachmentExt(FilenameUtils.getExtension(file.getOriginalFilename()));
                    attachment.setAttachmentType(AttachmentTypeEnum.IMAGE.getValue());
                    attachment.setAttachmentMd5(md5);
                    attachment.setCreateBy(-1);

                    attachmentService.save(attachment);

                    attachment.setPath("http://"+upload.getUrl());
                } else {
                    attachment.setPath(String.format("http://%s/%s", QiniuUtils.getUrlPrefix(), attachment.getPath()));
                }

                list.add(attachment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return SimpleResult.responseOk(list,"ok");
    }
}
