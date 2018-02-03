DROP TABLE IF EXISTS m_friend;
DROP TABLE IF EXISTS m_group;
DROP TABLE IF EXISTS m_group_user;
DROP TABLE IF EXISTS m_visit;
DROP TABLE IF EXISTS m_feed_attach;
DROP TABLE IF EXISTS m_feed_summary;
DROP TABLE IF EXISTS m_comment;
DROP TABLE IF EXISTS m_focus;

CREATE TABLE m_friend(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  user_id INTEGER,
  friend_id INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
);
CREATE TABLE m_group(
  id INTEGER AUTO_INCREMENT NOT NULL,
  groupname VARCHAR(20),
  group_owner INTEGER,
  avatar VARCHAR(100),
  group_state INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  create_by INTEGER,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
);
CREATE TABLE m_group_user(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  group_id INTEGER,
  user_id INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
);
CREATE TABLE m_visit(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  user_id INTEGER,
  visit_user_id INTEGER,
  visit_type INTEGER,
  visit_source_id INTEGER,
  visit_state INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
)COMMENT ='访问记录表';
CREATE TABLE m_feed_attach(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  feed_id INTEGER,
  attach_url VARCHAR(100),
  attach_type INTEGER,
  attach_state INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
)COMMENT ='动态附件表';
CREATE TABLE m_feed_summary(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  source_id INTEGER,
  up_vote INTEGER,
  down_vote INTEGER,
  comment_count INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
)
COMMENT ='动态统计表';
CREATE TABLE m_comment(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  parent_id INTEGER,
  relation_id INTEGER,
  content VARCHAR(100),
  comment_type INTEGER,
  comment_state INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id)
)COMMENT ='评论表';
CREATE TABLE m_focus(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  user_id INTEGER,
  focus_user_id INTEGER,
  focus_type INTEGER,
  focus_state INTEGER,
  create_by INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  isvalid BIT DEFAULT 1,
  PRIMARY KEY (id)
)COMMENT ='关注表';
