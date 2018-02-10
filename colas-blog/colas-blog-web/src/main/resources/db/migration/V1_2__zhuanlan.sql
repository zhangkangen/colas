DROP TABLE IF EXISTS blog_zhuanlan;
DROP TABLE IF EXISTS blog_zhuanlan_item;


CREATE TABLE blog_zhuanlan (
  id            INTEGER AUTO_INCREMENT NOT NULL,
  name          VARCHAR(100)           NOT NULL,
  memo          TEXT,
  create_time   DATETIME,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  create_by     INTEGER,
  is_stick      BIT       DEFAULT 1,
  is_valid      BIT       DEFAULT 1,
  read_count    INTEGER   DEFAULT 0,
  comment_count INTEGER   DEFAULT 0,
  PRIMARY KEY (id)
)
  COMMENT ='专栏';

CREATE TABLE blog_zhuanlan_item (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  zhuanlan_id INTEGER                NOT NULL,
  article_id  INTEGER                NOT NULL,
  sort        INTEGER,
  create_by   INTEGER,
  create_time DATETIME,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid    BIT       DEFAULT 1,
  PRIMARY KEY (id)
)
  COMMENT ='专栏文章关联表';