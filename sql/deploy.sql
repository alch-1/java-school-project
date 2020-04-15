drop database if exists magnet;
create database magnet;

-- use this database to create and populate the tables
use magnet;

-- create table(s)

-- #-------------- WALL TABLES ------------# --
CREATE TABLE login (
	#user_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	full_name VARCHAR(255) NOT NULL,
  pword VARCHAR(255) NOT NULL,
  PRIMARY KEY(username)
);

create table friends (
	username varchar(255) not null,
  friend_username varchar(255) not null,
  PRIMARY KEY(username, friend_username)
);

create table requests (
	username varchar(255) not null,
  request_username varchar(255) not null,
  PRIMARY KEY(username, request_username)
);
-- timestamp default current_timestamp 
create table user_posts (
	post_id INT NOT NULL auto_increment, 
  wall varchar(125) not null,
	ts varchar(255),
	username varchar(255) not null,
  content text not null,
  tag int,
  PRIMARY KEY(post_id)
);
create table gifts (
	username VARCHAR(255) NOT NULL,
	gift_name VARCHAR(255) NOT NULL,
    gift_quantity int NOT NULL,
	stat VARCHAR(255) NOT NULL,
    sender VARCHAR(255) NOT NULL,
  PRIMARY KEY(username, sender)
);
create table tag (
	post_id INT NOT NULL auto_increment, 
  tag_id int not null,
  PRIMARY KEY(post_id)
);

# store who liked and who disliked
create table thread (
	post_id INT NOT NULL,
  friend_username varchar(255) not null,
  liked int,
  disliked int,
  PRIMARY KEY(post_id, friend_username)
);

create table reply (
  reply_id int not null auto_increment,
  post_id INT NOT NULL,
  ts varchar(255), 
  friend_username varchar(255) not null,
  reply text not null,
  primary key(reply_id)
);

-- #-------------- FARMING TABLES ------------# --

# gifts left must be updated at the start of every day; check by using last_date. Update last_date to be the date where you update gifts to 5.
create table farmers(
  username varchar(255) not null, 
  fullname varchar(255) not null, 
  experience int not null,
  title varchar(255) not null,
  gold double not null,
  gifts_left int not null default 5,
  last_date date not null,
  PRIMARY KEY(username)
);

create table seeds(
	username varchar(255) not null,
  seed_name varchar(255) not null,
  quantity int not null,
  PRIMARY KEY(username, seed_name)
);

create table lands(
	username varchar(255) not null,
	land_number int not null,
	crop_name varchar(255) not null,
	quantity int not null,
  harvest_time timestamp not null,
  wilt_time timestamp not null,
  stolen_count int default 0,
  PRIMARY KEY(username, land_number)
);

-- # insert commands (needed for your application to work)
-- # insert into login values (1, "u", "test full name", "p");

