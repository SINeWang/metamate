SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `mm_i_ins`
-- ----------------------------
DROP TABLE IF EXISTS `mm_i_ins`;
CREATE TABLE `mm_i_ins` (
  `id` varchar(160) NOT NULL,
  `c_ts` datetime NOT NULL,
  `c_value` varchar(255) DEFAULT NULL,
  `c_ref_id` varchar(160) NOT NULL,
  `int_id` varchar(160) NOT NULL,
  `c_cm` varchar(160) NOT NULL,
  `p_cm` varchar(160) NOT NULL,
  `p_ts` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mm_m_crf`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_crf`;
CREATE TABLE `mm_m_crf` (
  `id` varchar(160) NOT NULL COMMENT 'id = hash(int_id, ref_id, exc_name, inc_name)',
  `int_id` varchar(160) NOT NULL,
  `ref_id` varchar(160) NOT NULL,
  `exc_name` varchar(32) DEFAULT NULL,
  `inc_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='cross-reference of intension';

-- ----------------------------
--  Table structure for `mm_m_ext`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_ext`;
CREATE TABLE `mm_m_ext` (
  `id` varchar(160) NOT NULL COMMENT 'id = hash(group, name, version)',
  `group` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `version` varchar(32) NOT NULL,
  `visibility` varchar(16) NOT NULL COMMENT 'the visibility of scope',
  `ds` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='extension of concept';

-- ----------------------------
--  Table structure for `mm_m_int`
-- ----------------------------
DROP TABLE IF EXISTS `mm_m_int`;
CREATE TABLE `mm_m_int` (
  `id` varchar(160) NOT NULL COMMENT 'int_id = hash(ext_id, ref_id)',
  `ext_id` varchar(160) NOT NULL,
  `ref_id` varchar(160) NOT NULL,
  `rename` varchar(32) NOT NULL DEFAULT '' COMMENT 'the alias name of ref_id ',
  `is_single` tinyint(1) NOT NULL,
  `visibility` varchar(16) NOT NULL COMMENT 'the visibility of scope',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='intension of concept';

SET FOREIGN_KEY_CHECKS = 1;