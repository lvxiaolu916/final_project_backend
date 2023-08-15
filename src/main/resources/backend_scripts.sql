create database portfolio;
use portfolio;

create table user (
  user_id int NOT NULL AUTO_INCREMENT,
  user_name varchar(20) DEFAULT NULL,
  user_sex varchar(20) DEFAULT NULL,
  principal_holdings decimal(18,2) DEFAULT NULL,
  primary key (user_id)
);
create table stock_trainsaction (
  stock_id int NOT NULL,
  user_id int NOT NULL,
  volume int DEFAULT NULL,
  trainsaction_status int DEFAULT NULL, -- 1 for sell, 0 for buy
  create_time timestamp not NULL,
  PRIMARY KEY (stock_id,user_id,create_time)
);
create table real_time_stock (
  stock_id int NOT NULL AUTO_INCREMENT,
  stock_name varchar(20) NOT NULL,
  current_price decimal(18,2) DEFAULT NULL,
  stock_margin int DEFAULT NULL,
  fluctuation decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (stock_id)
);
create table user_position (
  user_id int NOT NULL,
  stock_id int NOT NULL,
  volume int DEFAULT NULL,
  principal_input decimal(18,2) DEFAULT NULL,
  first_status int default NULL,
  PRIMARY KEY (user_id, stock_id)
);
create table stock_details (
  stock_id int NOT NULL AUTO_INCREMENT,
  stock_name varchar(20) NOT NULL,
  time timestamp not NULL,
  price decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (stock_id, time)
);