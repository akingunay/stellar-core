drop database if exists test1;
create database test1;
use test1;

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
    
    