DROP TABLE IF EXISTS blog_user;
DROP TABLE IF EXISTS blog_article;
DROP TABLE IF EXISTS blog_tag;
DROP TABLE IF EXISTS blog_article_tag;
DROP TABLE IF EXISTS blog_comment;

CREATE TABLE  blog_user (
  id       INTEGER AUTO_INCREMENT NOT NULL,
  username VARCHAR(20)            NOT NULL,
  password VARCHAR(100),
  email    VARCHAR(20),
  phone    VARCHAR(20),
  nickname VARCHAR(20),
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
);

CREATE TABLE  blog_article (
  id             INTEGER AUTO_INCREMENT NOT NULL,
  title          VARCHAR(20),
  cover_img      VARCHAR(255) COMMENT '封面图',
  memo           VARCHAR(255),
  content        TEXT,
  create_time    DATETIME,
  publish_time   DATETIME,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  article_type   INTEGER COMMENT '',
  article_status INTEGER,
  is_stick       BIT       DEFAULT 0,
  read_count     INTEGER   DEFAULT 0
  COMMENT '阅读量',
  up_vote        INTEGER   DEFAULT 0
  COMMENT '推荐量',
  comment_count  INTEGER   DEFAULT 0
  COMMENT '评论量',
  create_by      INTEGER                NOT NULL,
  is_valid       BIT       DEFAULT 1,
  PRIMARY KEY (id)
);

CREATE TABLE  blog_tag (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  name        VARCHAR(20),
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  create_by   INTEGER,
  is_valid    BIT       DEFAULT 1,
  PRIMARY KEY (id)
);

CREATE TABLE  blog_article_tag (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  article_id  INTEGER                NOT NULL,
  tag_id      INTEGER                NOT NULL,
  create_by   INTEGER                NOT NULL,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE  blog_comment (
  id             INTEGER AUTO_INCREMENT NOT NULL,
  article_id     INTEGER,
  user_id        INTEGER,
  parent_id      INTEGER   DEFAULT -1,
  content        VARCHAR(255),
  create_time    DATETIME,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  comment_type   INTEGER,
  comment_status INTEGER,
  is_valid       BIT       DEFAULT 1,
  PRIMARY KEY (id)
);