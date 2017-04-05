SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `mm_i_ins`
-- ----------------------------
DROP TABLE IF EXISTS `mm_i_ins`;
CREATE TABLE `mm_i_ins` (
  `id` varchar(160) NOT NULL,
  `ext_id` varchar(160) NOT NULL,
  `int_id` varchar(160) NOT NULL,
  `owner_id` varchar(160) NOT NULL,
  `field` varchar(32) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `value_set_hash` varchar(160) DEFAULT NULL,
  `value_ref_id` varchar(160) DEFAULT NULL,
  `operator_id` varchar(160) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`begin_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mm_i_tag`
-- ----------------------------
DROP TABLE IF EXISTS `mm_i_tag`;
CREATE TABLE `mm_i_tag` (
  `tag_id` varchar(160) NOT NULL,
  `owner_id` varchar(160) NOT NULL,
  `ext_id` varchar(160) NOT NULL,
  `visibility` varchar(16) NOT NULL,
  `operator_id` varchar(160) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tag_id`,`owner_id`,`ext_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mm_m_tag`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_tag`;
CREATE TABLE `mm_m_tag` (
  `id` varchar(160) NOT NULL COMMENT 'id = hash(owner_id, ext_id, int_id, name)',
  `owner_id` varchar(160) NOT NULL,
  `ext_id` varchar(160) NOT NULL,
  `int_id` varchar(160) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT 'tag_name',
  `visibility` varchar(16) NOT NULL,
  `created_at` datetime NOT NULL,
  UNIQUE KEY `mm_m_tag_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='model_tag';

-- ----------------------------
--  Table structure for `mm_m_ext`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_ext`;
CREATE TABLE `mm_m_ext` (
  `id` varchar(160) NOT NULL COMMENT 'id = hash(owner_id, group, name, tree)',
  `group` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `tree` varchar(32) NOT NULL,
  `owner_id` varchar(160) NOT NULL,
  `visibility` varchar(16) NOT NULL COMMENT 'the visibility of scope',
  `structure` varchar(16) DEFAULT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`begin_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='extension of concept';

-- ----------------------------
--  Table structure for `mm_m_int`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_int`;
CREATE TABLE `mm_m_int` (
  `id` varchar(160) NOT NULL COMMENT 'int_id = hash(ext_id, ref_id)',
  `ext_id` varchar(160) NOT NULL,
  `field` varchar(32) NOT NULL DEFAULT '' COMMENT 'the alias name of ref_id ',
  `is_single` tinyint(1) NOT NULL,
  `visibility` varchar(16) NOT NULL COMMENT 'the visibility of scope',
  `structure` varchar(16) DEFAULT NULL,
  `ref_ext_id` varchar(160) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`begin_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='intension of concept';


-- ----------------------------
--  Table structure for `mm_m_crf`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_crf`;
CREATE TABLE `mm_m_crf` (
  `id` varchar(160) NOT NULL COMMENT 'id = hash(int_id, exc_name, inc_name)',
  `int_id` varchar(160) NOT NULL,
  `exc_name` varchar(32) DEFAULT NULL,
  `inc_name` varchar(32) DEFAULT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='cross-reference of intension';


SET FOREIGN_KEY_CHECKS = 1;
