package com.zhang.colas.common;


import java.io.Serializable;

/**
 * @author zxk
 * @date 2018-01-14 21:13:20
 */
public class SimpleResult implements Serializable {

    private Boolean success;
    private String msg;
    private Integer code;
    private Object data;
    private Integer errorCode;
    private String errorMsg;

    public static SimpleResult responseOk(String msg) {
        SimpleResult result = new SimpleResult();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    public static SimpleResult responseError(String msg){
        SimpleResult result = new SimpleResult();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public static SimpleResult responseOk(Object data,String msg){
        SimpleResult result = new SimpleResult();
        result.setSuccess(true);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
