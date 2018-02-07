
ALTER TABLE blog_user MODIFY password varchar(100);

ALTER TABLE blog_article ADD COLUMN is_stick BIT DEFAULT 0 COMMENT '是否置顶';