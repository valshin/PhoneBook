#execute this queries if you need create the database too:
#CREATE DATABASE IF NOT EXISTS `phonebook_by_valshin` DEFAULT CHARSET=utf8;
#USE `phonebook_by_valshin`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `fio` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `notes` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner` INT UNSIGNED NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `second_name` VARCHAR(64) NOT NULL,
  `last_name` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `home_phone` VARCHAR(15) DEFAULT NULL,
  `address` VARCHAR(200) DEFAULT NULL,
  `email` VARCHAR(64) DEFAULT NULL,
  FOREIGN KEY (`owner`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
