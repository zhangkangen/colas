package com.zhang.colas.blog.enums;

/**
 * 博客类别枚举
 *
 * @author lenovo
 */
public enum ArticleTypeEnum {

    ARTICLE_ORIGINAL(1, "原创"),
    ARTICLE_REPRINTED(2, "转载"),
    ARTICLE_TRANSLATE(3, "翻译");

    private ArticleTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
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
