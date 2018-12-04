drop database if exists mutualexclusion;
create database mutualexclusion;
use mutualexclusion;

create table if not exists offer (
    pid varchar(40) not null,
    item varchar(40) not null,
    primary key (pID)
);

create table if not exists accept (
    pid varchar(40) not null,
    item varchar(40) not null,
    resp varchar(40) not null,
    primary key (pid)
);

create table if not exists reject (
    pid varchar(40) not null,
    item varchar(40) not null,
    resp varchar(40) not null,
    primary key (pid)
);

insert into offer (pid, item) values
	("1", "i1"),
    ("2", "i2"),
    ("3", "i3"),
    ("4", "i4"),
    ("5", "i5"),
    ("6", "i6");
    
insert into accept (pid, item, resp) values
	("1", "i1", "r1"),
    ("2", "i2", "r2");

insert into reject (pid, item, resp) values
	("3", "i3", "r3"),
    ("4", "i4", "r4");
