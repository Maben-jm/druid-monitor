-- 创建数据库
create database test_m character set  utf8;
-- 使用该数据库
use test_m;
-- 创建表
CREATE TABLE `t_student` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8