DROP TABLE  if EXISTS m_feed;
CREATE TABLE m_feed(
  id INTEGER AUTO_INCREMENT NOT NULL ,
  user_id INTEGER NOT NULL ,
  feed_content VARCHAR(255),
  feed_type INTEGER,
  feed_state INTEGER,
  create_time TIMESTAMP DEFAULT current_timestamp,
  create_by INTEGER,
  is_valid BIT DEFAULT 1,
  PRIMARY KEY (id),
  KEY (user_id)
) COMMENT = '动态表'