DROP TABLE IF EXISTS c_album;
CREATE TABLE c_album(
  id INTEGER AUTO_INCREMENT,
  title VARCHAR(20) NOT NULL ,
  memo VARCHAR(100) ,
  user_id INTEGER,
  album_cover VARCHAR(100),
  album_type INTEGER,
  album_state INTEGER,
  photo_num INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT current_timestamp,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id),
  KEY (user_id)
) COMMENT = '相册表';

DROP TABLE IF EXISTS c_photo;
CREATE TABLE  c_photo(
  id INTEGER AUTO_INCREMENT,
  title VARCHAR(20) NOT NULL ,
  memo VARCHAR(100),
  user_id INTEGER,
  album_id INTEGER,
  photo_url VARCHAR(100),
  photo_type INTEGER,
  photo_state INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
)COMMENT = '照片表';

drop TABLE IF EXISTS c_attachment_relation;
CREATE TABLE c_attachment_relation(
  id INTEGER AUTO_INCREMENT,
  attachment_id INTEGER,
  relation_type INTEGER,
  relation_id INTEGER,
  memo VARCHAR(20),
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
) COMMENT= '附件关联表';

