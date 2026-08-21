CREATE DATABASE IF NOT EXISTS UserApp1;

CREATE TABLE UserApp1.users (
    `user_id` INT AUTO_INCREMENT,
    `username` VARCHAR(255),
    `email` VARCHAR(255),
PRIMARY KEY (user_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE UserApp1.builders (
    `builder_id` INT AUTO_INCREMENT,
    `name` VARCHAR(255),
    `description` VARCHAR(255),
PRIMARY KEY (builder_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
