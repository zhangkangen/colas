package com.zhang.colas.core.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zhang.colas.core.entity.QiniuProperties;
import com.zhang.colas.core.entity.QiniuUploadResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.SpringProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.UUID;

public class QiniuUtils {

    private static final String PROPERTIES_RESOURCE_LOCATION = "config/qiniu.properties";

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
                qiniuProperties = new QiniuProperties();
                qiniuProperties.setAccessKey(localProperties.getProperty("qiniu.oss.accessKey"));
                qiniuProperties.setSecretKey(localProperties.getProperty("qiniu.oss.secretKey"));
                qiniuProperties.setBucket(localProperties.getProperty("qiniu.oss.bucket"));
                qiniuProperties.setPrefix(localProperties.getProperty("qiniu.oss.prefix"));

                Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
                token = auth.uploadToken(qiniuProperties.getBucket());
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QiniuUploadResult upload(InputStream stream, String originalFilename) {

        if(StringUtils.isBlank(token)){
            return QiniuUploadResult.uploadError("未获取到token");
        }
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
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String generatorName() {
        return LocalDate.now().toString() + "/" + UUID.randomUUID().toString();
    }

}
