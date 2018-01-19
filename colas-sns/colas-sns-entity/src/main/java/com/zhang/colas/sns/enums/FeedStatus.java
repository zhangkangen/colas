package com.zhang.colas.sns.enums;

public enum FeedStatus {

    ;
    private FeedStatus(String desc,Integer value){
        this.desc = desc;
        this.value = value;
    }

    private String desc;
    private Integer value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
