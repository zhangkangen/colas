package com.zhang.colas.blog.enums;

public enum AttachmentTypeEnum {
    IMAGE(1,"图片")
    ;

    private AttachmentTypeEnum(Integer value,String desc){
        this.desc = desc;
        this.value = value;
    }

    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
