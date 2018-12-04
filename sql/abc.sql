drop database if exists ahistory;
create database ahistory;
use ahistory;

create table if not exists msga (
    id varchar(100) not null,
    p1 varchar(100) not null,
    primary key (id)
);

create table if not exists msgb (
    id varchar(100) not null,
    p1 varchar(100) not null,
    p2 varchar(100) not null,
    primary key (id)
);

create table if not exists msgc (
    id varchar(100) not null,
    p2 varchar(100) not null,
    p3 varchar(100) not null,
    primary key (id)
);

drop database if exists bhistory;
create database bhistory;
use bhistory;

create table if not exists msga (
    id varchar(100) not null,
    p1 varchar(100) not null,
    primary key (id)
);

create table if not exists msgb (
    id varchar(100) not null,
    p1 varchar(100) not null,
    p2 varchar(100) not null,
    primary key (id)
);

create table if not exists msgc (
    id varchar(100) not null,
    p2 varchar(100) not null,
    p3 varchar(100) not null,
    primary key (id)
);

drop database if exists chistory;
create database chistory;
use chistory;

create table if not exists msga (
    id varchar(100) not null,
    p1 varchar(100) not null,
    primary key (id)
);

create table if not exists msgb (
    id varchar(100) not null,
    p1 varchar(100) not null,
    p2 varchar(100) not null,
    primary key (id)
);

create table if not exists msgc (
    id varchar(100) not null,
    p2 varchar(100) not null,
    p3 varchar(100) not null,
    primary key (id)
);
