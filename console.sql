# CREATE DATABASE IF NOT EXISTS how2java DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE how2java;

CREATE TABLE IF NOT EXISTS category_ (
  id   int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32)      DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

DELETE FROM category_;
INSERT INTO category_ VALUES (null, 'category1');
INSERT INTO category_ VALUES (null, 'category2');

create table product_ (
  id    int not null  auto_increment,
  name  varchar(30)   default null,
  price float         default 0,
  cid   int,
  primary key (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DELETE FROM product_;
INSERT INTO product_ VALUES (1, 'product a', 88.88, 1);
INSERT INTO product_ VALUES (2, 'product b', 88.88, 1);
INSERT INTO product_ VALUES (3, 'product c', 88.88, 1);
INSERT INTO product_ VALUES (4, 'product x', 88.88, 2);
INSERT INTO product_ VALUES (5, 'product y', 88.88, 2);
INSERT INTO product_ VALUES (6, 'product z', 88.88, 2);

create table order_ (
  id   int(11) NOT NULL AUTO_INCREMENT,
  code varchar(32)      DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO order_ VALUES (1,'code000A');
INSERT INTO order_ VALUES (2,'code000B');


create table order_item_ (
  id     int(11) NOT NULL AUTO_INCREMENT,
  oid    int,
  pid    int,
  number int,
  PRIMARY KEY (id)
)
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO order_item_ VALUES (null, 1, 1, 100);
INSERT INTO order_item_ VALUES (null, 1, 2, 100);
INSERT INTO order_item_ VALUES (null, 1, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 2, 100);
INSERT INTO order_item_ VALUES (null, 2, 3, 100);
INSERT INTO order_item_ VALUES (null, 2, 4, 100);

# select * from category_ where name like 'cat%';
# insert into category_ value (0, 'category3');

select
  c.*,
  p.*,
  c.id   'cid',
  p.id   'pid',
  c.name 'cname',
  p.name 'pname'
from category_ c left join product_ p on c.id = p.cid;

select
  c.*,
  p.*,
  c.id   'cid',
  p.id   'pid',
  c.name 'cname',
  p.name 'pname'
from category_ c left join product_ p on c.id = p.cid;

select
  o.*,
  p.*,
  oi.*,
  o.id   'oid',
  p.id   'pid',
  oi.id  'oiid',
  p.name 'pname'
from order_ o
  left join order_item_ oi on o.id = oi.oid
  left join product_ p on p.id = oi.pid

