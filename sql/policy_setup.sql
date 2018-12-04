drop database if exists policy;
create database policy;
use policy;

create table if not exists createpolicy (
    pid varchar(40) not null,
    pinfo varchar(40) not null,
    primary key (pID)
);

create table if not exists makeclaim (
    pid varchar(40) not null,
    cid varchar(40) not null,
    claimed varchar(40) not null,
    primary key (pid, cid)
);

create table if not exists payclaim (
    pid varchar(40) not null,
    cid varchar(40) not null,
    claimed varchar(40) not null,
    paid varchar(40) not null,
    primary key (pid, cid)
);

insert into createpolicy (pid, pinfo) values
	("1", "i1"),
    ("2", "i2"),
    ("3", "i3"),
    ("4", "i4");
    
insert into makeclaim (pid, cid, claimed) values
	("1", "1", "c11"),
    ("1", "2", "c12"),
    ("1", "3", "c13"),
    ("2", "1", "c21"),
    ("2", "2", "c22"),
    ("3", "1", "c31");

insert into payclaim (pid, cid, claimed, paid) values
	("1", "1", "c11", "p11"),
    ("1", "2", "c12", "p12"),
    ("2", "1", "c21", "p21");
