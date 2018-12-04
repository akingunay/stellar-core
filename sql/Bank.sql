drop database if exists bankhistory;
create database bankhistory;
use bankhistory;

create table if not exists charge (
pid varchar(100) not null,
price varchar(100) not null,
ccard varchar(100) not null,
account varchar(100) not null,
    primary key (pid)
);

create table if not exists transfer (
pid varchar(100) not null,
price varchar(100) not null,
ccard varchar(100) not null,
account varchar(100) not null,
chargeResponse varchar(100) not null,
receipt varchar(100) not null,
    primary key (pid)
);

create table if not exists deny (
pid varchar(100) not null,
price varchar(100) not null,
ccard varchar(100) not null,
account varchar(100) not null,
chargeResponse varchar(100) not null,
denialReason varchar(100) not null,
    primary key (pid)
);

