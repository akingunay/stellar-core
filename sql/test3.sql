drop database if exists test3;
create database test3;
use test3;

create table if not exists m1 (
    k1 varchar(40) not null,	# OUT
    p1 varchar(40) not null,	# OUT
    primary key (k1)
);

create table if not exists m2 (
    k1 varchar(40) not null,
    p1 varchar(40) not null,
	p2 varchar(40) not null,	# OUT
    primary key (k1)
);

create table if not exists m3 (
    k1 varchar(40) not null,
    p1 varchar(40) not null,
    p2 varchar(40) not null,	# OUT
    p3 varchar(40) not null,	# OUT
    primary key (k1)
);

create table if not exists m4 (
    k1 varchar(40) not null,
    p1 varchar(40) not null,
    p2 varchar(40) not null,
    p3 varchar(40),
    p4 varchar(40) not null,	# OUT
    primary key (k1)
);

create table if not exists m5 (
    k1 varchar(40) not null,
    p1 varchar(40) not null,
    p2 varchar(40) not null,
    p3 varchar(40) not null,	# OUT
    p4 varchar(40) not null,
    p5 varchar(40) not null,	# OUT
    primary key (k1)
);

insert into m1 (k1, p1) values
	("1", "p1-01"),
    ("2", "p1-02"),
    ("3", "p1-03"),
    ("4", "p1-04"),
	("5", "p1-05"),
    ("6", "p1-06"),
    ("7", "p1-07"),
    ("8", "p1-08"),
	("9", "p1-09"),
    ("10", "p1-10");

insert into m2 (k1, p1, p2) values
	("1", "p1-01", "p2-01"),
    ("2", "p1-02", "p2-02"),
    ("3", "p1-03", "p2-03"),
    ("4", "p1-04", "p2-04"),
	("5", "p1-05", "p2-05"),
    ("6", "p1-06", "p2-06");

insert into m3 (k1, p1, p2, p3) values
	("7", "p1-07", "p2-07", "p3-07"),
    ("8", "p1-08", "p2-08", "p3-08");
    
insert into m4 (k1, p1, p2, p3, p4) values
	("1", "p1-01", "p2-01", NULL, "p4-01"),
    ("2", "p1-02", "p2-02", NULL, "p4-02"),
    ("3", "p1-03", "p2-03", NULL, "p4-03"),
    ("4", "p1-04", "p2-04", NULL, "p4-04");
    
insert into m5 (k1, p1, p2, p3, p4, p5) values
	("1", "p1-01", "p2-01", "p3-01", "p4-01", "p5-01"),
    ("2", "p1-02", "p2-02", "p3-02", "p4-02", "p5-02");

CREATE VIEW view1 AS SELECT m1.k1, m1.p1 FROM m1;

CREATE VIEW view2 AS SELECT m5.k1, view1.p1 FROM m5 LEFT JOIN view1 ON m5.k1 = view1.k1 UNION SELECT view1.k1, view1.p1 FROM view1 WHERE (view1.k1) NOT IN (SELECT m5.k1 FROM m5);

CREATE VIEW view3 AS SELECT view2.k1, view2.p1, m5.p2, m5.p3, m5.p4, m5.p5 FROM view2 LEFT JOIN m5 ON view2.k1 = m5.k1;

SELECT view3.k1, view3.p1, view3.p2, view3.p3, view3.p4, view3.p5 FROM view3 ORDER BY k1+"0";
    