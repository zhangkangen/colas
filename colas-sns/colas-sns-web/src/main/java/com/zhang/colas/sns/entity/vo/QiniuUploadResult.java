package com.zhang.colas.sns.entity.vo;

public class QiniuUploadResult {


    private boolean success;
    private String path;
    private String url;


    public static QiniuUploadResult uploadOk(String path,String url){
        QiniuUploadResult result = new QiniuUploadResult();
        result.setPath(path);
        result.setUrl(url);
        result.setSuccess(true);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
