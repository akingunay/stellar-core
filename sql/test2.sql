drop database if exists test2;
create database test2;
use test2;

create table if not exists m1 (
    k1 varchar(40) not null,
    p1 varchar(40),
	p2 varchar(40),
    primary key (k1)
);

create table if not exists m2 (
    k1 varchar(40) not null,
    p2 varchar(40),
	p3 varchar(40),
    primary key (k1)
);

insert into m1 (k1, p1, p2) values
	("1", "p1-01", "p2-01"),
    ("2", "p1-02", "p2-02"),
    ("3", "p1-03", "p2-03"),
    ("4", "p1-04", "p2-04"),
    ("5", NULL, "p2-05"),
    ("6", NULL, "p2-06"),
    ("7", NULL, "p2-07"),
    ("8", NULL, "p2-08"),
	("9", "p1-09", NULL),
    ("10", "p1-10", NULL),
	("11", "p1-11", NULL),
    ("12", "p1-12", NULL),
    ("13", NULL, NULL),
    ("14", NULL, NULL),
    ("15", NULL, NULL),
    ("16", NULL, NULL);
    
insert into m2 (k1, p2, p3) values
	("1", "p2-01", "p3-01"),
    ("2", "p2-02", "p3-02"),
    ("5", "p2-05", "p3-05"),
    ("6", "p2-06", "p3-06"),
	("9", "p2-09", "p3-09"),
    ("10", "p2-10", "p3-10"),
	("13", "p2-13", "p3-13"),
    ("14", "p2-14", "p3-14"),
	("21", "p2-21", "p3-21"),
    ("22", "p2-22", "p3-22"),
    ("25", "p2-25", "p3-25"),
    ("26", "p2-26", "p3-26"),
	("29", "p2-29", "p3-29"),
    ("30", "p2-30", "p3-20"),
	("33", "p2-33", "p3-23"),
    ("34", "p2-34", "p3-24");

CREATE VIEW v12 AS
SELECT m2.k1, m1.p1, m2.p2 FROM m2 LEFT JOIN m1 ON m2.k1 = m1.k1
UNION
SELECT m1.k1, m1.p1, m1.p2 FROM m1 WHERE m1.k1 NOT IN (SELECT m2.k1 FROM m2);

CREATE VIEW v120 AS
SELECT v12.k1, v12.p1, v12.p2, m2.p3 FROM v12 LEFT JOIN m2 ON v12.k1 = m2.k1;

SELECT * FROM v12  ORDER BY k1+0;
SELECT * FROM v120  ORDER BY k1+0;


SELECT * FROM v120 WHERE k1 = "25";

create table if not exists m3 (
    k1 varchar(40) not null,
    k2 varchar(40) not null,
    p2 varchar(40),
	p3 varchar(40),
    primary key (k1, k2)
);

insert into m3 (k1, k2, p2, p3) values
	("1", "1", "p2-01", "p3-01"),
    ("2", "1", "p2-02", "p3-02"),
    ("5", "2", "p2-05", "p3-05"),
    ("6", "2", "p2-06", "p3-06"),
	("9", "3", "p2-09", "p3-09"),
    ("10", "3", "p2-10", "p3-10"),
	("13", "4", "p2-13", "p3-13"),
    ("14", "4", "p2-14", "p3-14"),
	("21", "5", "p2-21", "p3-21"),
    ("22", "5", "p2-22", "p3-22"),
    ("25", "6", "p2-25", "p3-25"),
    ("26", "6", "p2-26", "p3-26"),
	("29", "7", "p2-29", "p3-29"),
    ("30", "7", "p2-30", "p3-20"),
	("33", "8", "p2-33", "p3-23"),
    ("34", "8", "p2-34", "p3-24");

CREATE VIEW v13 AS
SELECT m3.k1, m1.p1, m3.p2 FROM m3 LEFT JOIN m1 ON m3.k1 = m1.k1
UNION
SELECT m1.k1, m1.p1, m1.p2 FROM m1 WHERE m1.k1 NOT IN (SELECT m3.k1 FROM m3);

CREATE VIEW v130 AS
SELECT v13.k1, m3.k2, v13.p1, v13.p2, m3.p3 FROM v13 LEFT JOIN m3 ON v13.k1 = m3.k1;

SELECT * FROM v13  ORDER BY k1+0;
SELECT * FROM v130  ORDER BY k1+0;
SELECT * FROM v130 WHERE k2 = "6";
SELECT * FROM v130 WHERE k1 = "25" AND k2 = "6"  ;
