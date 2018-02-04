package com.zhang.colas.core.entity;

import java.io.Serializable;

public class QiniuUploadResult implements Serializable {


    private static final long serialVersionUID = -7683663186423205919L;
    private boolean success;
    private String path;
    private String url;
    private String msg;


    public static QiniuUploadResult uploadOk(String path,String url){
        QiniuUploadResult result = new QiniuUploadResult();
        result.setPath(path);
        result.setUrl(url);
        result.setSuccess(true);
        return result;
    }

    public static QiniuUploadResult uploadError(String msg){
        QiniuUploadResult result = new QiniuUploadResult();
        result.setSuccess(false);
        result.setMsg(msg);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
