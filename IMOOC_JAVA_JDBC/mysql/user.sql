CREATE DATABASE jdbctest;
USE jdbctest;

CREATE TABLE IF NOT EXISTS user(
	`uid` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(20),
    `password` VARCHAR(20),
    `name` VARCHAR(20)
);

INSERT INTO user VALUES(null, 'hzb', '1234', '黄泽彬');
INSERT INTO user VALUES(null, 'xj', '1234', '熊君');
INSERT INTO user VALUES(null, 'hwh', '1234', '胡文瀚');

SELECT * FROM user;