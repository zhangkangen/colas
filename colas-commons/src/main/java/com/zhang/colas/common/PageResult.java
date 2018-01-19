package com.zhang.colas.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 */
public class PageResult implements Serializable {
    private static final long serialVersionUID = 3329072987718239696L;

    private long sinceId;
    private long maxId;
    private Integer limit = 10;
    private Boolean success;
    private Integer status;
    private Object data;
    private String message;
    private Integer errorCode;
    private String errorMessage;

    public PageResult() {

    }

    public PageResult pullOk(long maxId, Object data, String msg) {
        PageResult pageResult = new PageResult();
        pageResult.setStatus(HttpStatus.OK.value());
        pageResult.setSuccess(true);
        pageResult.setData(data);
        pageResult.setMaxId(maxId);
        pageResult.setMessage(msg);
        return pageResult;
    }

    public PageResult freshOk(long sinceId, Object data, String msg) {
        PageResult pageResult = new PageResult();
        pageResult.setStatus(HttpStatus.OK.value());
        pageResult.setSinceId(sinceId);
        pageResult.setSuccess(true);
        pageResult.setData(data);
        pageResult.setMessage(msg);
        return pageResult;
    }

    public PageResult error(String msg) {
        PageResult pageResult = new PageResult();
        pageResult.setSuccess(false);
        pageResult.setStatus(HttpStatus.BAD_REQUEST.value());
        pageResult.setMessage(msg);
        return pageResult;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
