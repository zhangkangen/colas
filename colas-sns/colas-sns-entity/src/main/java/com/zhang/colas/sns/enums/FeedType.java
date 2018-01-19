package com.zhang.colas.sns.enums;

public enum FeedType {

    /**
     * 个人
     */
    PERSONAL("个人",1),
    /**
     * 系统
     */
    SYSTEM("系统",2);

    private FeedType(String desc,Integer value){
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
