package com.zhang.colas.sns.web;

import com.zhang.colas.common.response.SimpleResult;
import com.zhang.colas.sns.utils.QiniuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author zxk
 * @date 2018-01-14 21:09:41
 */
@Controller
@RequestMapping("attachment")
public class AttachmentController {

    @RequestMapping("imageUpload")
    public String uploadImage(MultipartFile file) {

        InputStream stream = null;
        try {
            stream = file.getInputStream();
            QiniuUtils.upload(stream, file.getOriginalFilename());
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
