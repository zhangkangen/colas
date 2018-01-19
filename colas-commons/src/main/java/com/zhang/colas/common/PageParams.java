package com.zhang.colas.common;

import java.io.Serializable;

/**
 * 分页参数
 */
public class PageParams implements Serializable {
    private static final long serialVersionUID = -7759623105795137222L;

    private long sinceId;
    private long maxId;
    private Integer limit = 10;

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
}
