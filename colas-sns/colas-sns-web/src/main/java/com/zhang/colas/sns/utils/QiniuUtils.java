package com.zhang.colas.sns.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zhang.colas.sns.config.QiniuProperties;
import com.zhang.colas.sns.entity.vo.QiniuUploadResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.SpringProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

@Component
public class QiniuUtils {

    private static final String PROPERTIES_RESOURCE_LOCATION = "spring.properties";

    @Resource
    private QiniuProperties qiniuPropertiesAutowired;

    private static QiniuProperties qiniuProperties;

    private static String token;

    private static Properties localProperties = new Properties();

    static {
        try {
            ClassLoader cl = SpringProperties.class.getClassLoader();
            URL url = cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) : ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION);
            InputStream is = url.openStream();
            try {
                localProperties.load(is);
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        qiniuProperties = this.qiniuPropertiesAutowired;
        try {
            Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
            token = auth.uploadToken(qiniuProperties.getBucket());
        } catch (Exception e) {

        }
    }

    public static QiniuUploadResult upload(InputStream stream, String originalFilename) {

        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null;
        try {

            String ext = FilenameUtils.getExtension(originalFilename);
            key = generatorName() + "." + ext;


            Response response = uploadManager.put(stream, key, token, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            return QiniuUploadResult.uploadOk(key, qiniuProperties.getPrefix() + "/" + key);

        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generatorName() {
        return LocalDate.now().toString() + "/" + UUID.randomUUID().toString();
    }


}
