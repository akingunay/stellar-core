drop database if exists sellerhistory;
create database sellerhistory;
use sellerhistory;

create table if not exists rfq (
pid varchar(100) not null,
item varchar(100) not null,
    primary key (pid)
);

create table if not exists quote (
pid varchar(100) not null,
item varchar(100) not null,
price varchar(100) not null,
    primary key (pid)
);

create table if not exists acceptQuote (
pid varchar(100) not null,
item varchar(100) not null,
price varchar(100) not null,
quoteResponse varchar(100) not null,
ccard varchar(100) not null,
    primary key (pid)
);

create table if not exists rejectQuote (
pid varchar(100) not null,
item varchar(100) not null,
price varchar(100) not null,
quoteResponse varchar(100) not null,
outcome varchar(100) not null,
    primary key (pid)
);

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

create table if not exists goods (
pid varchar(100) not null,
item varchar(100) not null,
receipt varchar(100) not null,
outcome varchar(100) not null,
    primary key (pid)
);

create table if not exists error (
pid varchar(100) not null,
item varchar(100) not null,
denialReason varchar(100) not null,
outcome varchar(100) not null,
    primary key (pid)
);

