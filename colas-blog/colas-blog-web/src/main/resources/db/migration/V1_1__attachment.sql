DROP TABLE IF EXISTS `c_attachment`;

CREATE TABLE `c_attachment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30)  DEFAULT NULL COMMENT '文件名',
  `path` varchar(100)  DEFAULT NULL COMMENT '路径',
  `temp_name` varchar(64)  DEFAULT NULL COMMENT '生成名',
  `attachment_size` bigint(20) DEFAULT NULL COMMENT '附件大小',
  `attachment_ext` varchar(10) DEFAULT NULL COMMENT '后缀名',
  `attachment_type` int(11) DEFAULT NULL COMMENT '1:图片 2:视频 3:文档',
  `attachment_md5` varchar(32) DEFAULT NULL COMMENT '文件md5',
  `create_by` int(11) DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_valid` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `c_attachment_relation`;
CREATE TABLE `c_attachment_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relation_id` int(11) DEFAULT NULL,
  `relation_type` int(11) DEFAULT NULL COMMENT '1:博客',
  `create_by` int(11) DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_valid` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`) USING BTREE
);
