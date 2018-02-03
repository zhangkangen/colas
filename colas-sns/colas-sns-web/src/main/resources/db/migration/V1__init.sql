create TABLE c_attachment(
  id INTEGER AUTO_INCREMENT,
  attachment_name VARCHAR(20),
  attachment_suffix VARCHAR(10),
  attachment_size BIGINT,
  attachment_type INTEGER,
  attachment_path VARCHAR(30),
  temp_name VARCHAR(20),
  is_valid BIT DEFAULT 1,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  ,PRIMARY KEY (id)
) COMMENT = '附件表';
create TABLE s_user(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  username VARCHAR(20) NOT NULL ,
  password VARCHAR(20) DEFAULT '',
  user_email VARCHAR(20) ,
  github_id VARCHAR(20),
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
) COMMENT = '用户表';
CREATE TABLE s_user_profile(
  id INTEGER NOT NULL ,
  user_id INTEGER NOT NULL ,
  nickname VARCHAR(20),
  realname VARCHAR(20),
  avatar_url VARCHAR(100),
  create_by INTEGER NOT NULL ,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id),
  KEY (user_id)
) COMMENT = '用户详情'
