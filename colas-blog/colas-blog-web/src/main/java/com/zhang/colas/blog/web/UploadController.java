package com.zhang.colas.blog.web;

import com.zhang.colas.common.SimpleResult;
import com.zhang.colas.common.base.BaseController;
import com.zhang.colas.core.entity.QiniuUploadResult;
import com.zhang.colas.core.utils.QiniuUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @PostMapping("uploadImg")
    @ResponseBody
    public SimpleResult uploadImg(HttpServletRequest request, List<MultipartFile> files) {

        Iterator<MultipartFile> iterator = files.iterator();
        while (iterator.hasNext()) {
            MultipartFile file = iterator.next();
            LOGGER.info(file.getOriginalFilename());
            try {

                String md5 = DigestUtils.md5Hex(file.getBytes());
                LOGGER.info(md5);
                QiniuUploadResult upload = QiniuUtils.upload(file.getInputStream(), file.getOriginalFilename());

                LOGGER.info(upload.getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return SimpleResult.responseOk("ok");
    }
}
