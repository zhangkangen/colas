DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_menu;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_login;

CREATE TABLE sys_user (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  username    VARCHAR(20),
  password    VARCHAR(20),
  email       VARCHAR(20),
  user_status INTEGER,
  create_time DATETIME,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  create_by   INTEGER,
  is_valid    BIT       DEFAULT 1,
  PRIMARY KEY (id)
)
  COMMENT ='系统用户表';
CREATE TABLE sys_role (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  role_name   VARCHAR(20),
  role_desc   VARCHAR(100),
  role_rights VARCHAR(255),
  add_qx      VARCHAR(255),
  del_qx      VARCHAR(255),
  edit_qx     VARCHAR(255),
  query_qx    VARCHAR(255),
  user_id     INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_valid    BIT       DEFAULT 1,
  PRIMARY KEY (id)
)
  COMMENT ='系统角色表';

CREATE TABLE sys_user_role (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  user_id     INTEGER,
  role_id     INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  COMMENT ='人员角色关联表';

CREATE TABLE sys_menu (
  id          INTEGER AUTO_INCREMENT NOT NULL,
  parent_id   INTEGER,
  menu_name   VARCHAR(20),
  menu_url    VARCHAR(20),
  menu_type   INTEGER,
  menu_icon   VARCHAR(20),
  sort_num    INTEGER,
  user_id     INTEGER,
  is_valid    BIT       DEFAULT 1,
  create_time DATETIME,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  COMMENT = '菜单表';

CREATE TABLE sys_login (
  id              INTEGER AUTO_INCREMENT NOT NULL,
  user_id         INTEGER,
  last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  COMMENT ='登陆日志表';