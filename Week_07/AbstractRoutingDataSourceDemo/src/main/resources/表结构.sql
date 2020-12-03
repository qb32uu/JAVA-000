CREATE TABLE `rw` (
  `rw_id` bigint NOT NULL AUTO_INCREMENT COMMENT '读写分离ID',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `change_time` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`rw_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='读写分离';

