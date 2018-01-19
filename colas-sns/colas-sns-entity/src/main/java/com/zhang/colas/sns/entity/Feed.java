package com.zhang.colas.sns.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态表
 * @author zxk
 * @date 2018-01-20 00:49:06
 */
public class Feed  implements Serializable{

    private static final long serialVersionUID = -7630817508839775393L;

    private Integer id;

    private Integer userId;

    private String feedContent;

    private String feedImg;

    private Date feedTime;

    private Integer feedType;

    private Integer feedState;

    private Date createTime;

    private Integer createBy;

    private Boolean isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent == null ? null : feedContent.trim();
    }

    public String getFeedImg() {
        return feedImg;
    }

    public void setFeedImg(String feedImg) {
        this.feedImg = feedImg == null ? null : feedImg.trim();
    }

    public Date getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(Date feedTime) {
        this.feedTime = feedTime;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }

    public Integer getFeedState() {
        return feedState;
    }

    public void setFeedState(Integer feedState) {
        this.feedState = feedState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}