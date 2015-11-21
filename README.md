CREATE DATABASE IF NOT EXISTS `phonebook_by_valshin` DEFAULT CHARSET=utf8 ;
USE `phonebook_by_valshin` ;

CREATE TABLE IF NOT EXISTS `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `second_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `home_phone` varchar(12) DEFAULT NULL,
  `adress` varchar(200) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` int(128) NOT NULL,
  `pass` int(128) NOT NULL,
  `fio` int(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;