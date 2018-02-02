create TABLE m_answer(
  id INTEGER AUTO_INCREMENT PRIMARY KEY ,
  q_id INTEGER NOT NULL COMMENT '问题id',
  user_id INTEGER NOT NULL COMMENT '回答人',
  q_content TEXT COMMENT '回答内容',
  create_time TIMESTAMP COMMENT '创建时间',
  comment_count INTEGER COMMENT '评论数',
  upvote_count INTEGER COMMENT '点赞数'
) COMMENT ='回答表';

