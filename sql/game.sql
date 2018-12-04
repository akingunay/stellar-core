drop database if exists shistory;
create database shistory;
use shistory;

create table if not exists launchGame (
gID varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerP (
gID varchar(100) not null,
aliasP varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerK (
gID varchar(100) not null,
aliasK varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedP (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedK (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreP (
gID varchar(100) not null,
scoreP varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreK (
gID varchar(100) not null,
scoreK varchar(100) not null,
    primary key (gID)
);

create table if not exists stopP (
gID varchar(100) not null,
scoreK varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists stopK (
gID varchar(100) not null,
scoreP varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists gameOver (
gID varchar(100) not null,
scoreP varchar(100) not null,
scoreK varchar(100) not null,
outcome varchar(100) not null,
    primary key (gID)
);

create table if not exists fireP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
scoreP varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
damageP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultP (
gID varchar(100) not null,
fID varchar(100) not null,
damageP varchar(100) not null,
pointsP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists fireK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
scoreK varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
damageK varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultK (
gID varchar(100) not null,
fID varchar(100) not null,
damageK varchar(100) not null,
pointsK varchar(100) not null,
    primary key (gID, fID)
);

drop database if exists ehistory;
create database ehistory;
use ehistory;

create table if not exists launchGame (
gID varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerP (
gID varchar(100) not null,
aliasP varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerK (
gID varchar(100) not null,
aliasK varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedP (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedK (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreP (
gID varchar(100) not null,
scoreP varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreK (
gID varchar(100) not null,
scoreK varchar(100) not null,
    primary key (gID)
);

create table if not exists stopP (
gID varchar(100) not null,
scoreK varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists stopK (
gID varchar(100) not null,
scoreP varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists gameOver (
gID varchar(100) not null,
scoreP varchar(100) not null,
scoreK varchar(100) not null,
outcome varchar(100) not null,
    primary key (gID)
);

create table if not exists fireP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
scoreP varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
damageP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultP (
gID varchar(100) not null,
fID varchar(100) not null,
damageP varchar(100) not null,
pointsP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists fireK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
scoreK varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
damageK varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultK (
gID varchar(100) not null,
fID varchar(100) not null,
damageK varchar(100) not null,
pointsK varchar(100) not null,
    primary key (gID, fID)
);

drop database if exists phistory;
create database phistory;
use phistory;

create table if not exists launchGame (
gID varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerP (
gID varchar(100) not null,
aliasP varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerK (
gID varchar(100) not null,
aliasK varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedP (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedK (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreP (
gID varchar(100) not null,
scoreP varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreK (
gID varchar(100) not null,
scoreK varchar(100) not null,
    primary key (gID)
);

create table if not exists stopP (
gID varchar(100) not null,
scoreK varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists stopK (
gID varchar(100) not null,
scoreP varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists gameOver (
gID varchar(100) not null,
scoreP varchar(100) not null,
scoreK varchar(100) not null,
outcome varchar(100) not null,
    primary key (gID)
);

create table if not exists fireP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
scoreP varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
damageP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultP (
gID varchar(100) not null,
fID varchar(100) not null,
damageP varchar(100) not null,
pointsP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists fireK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
scoreK varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
damageK varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultK (
gID varchar(100) not null,
fID varchar(100) not null,
damageK varchar(100) not null,
pointsK varchar(100) not null,
    primary key (gID, fID)
);

drop database if exists khistory;
create database khistory;
use khistory;

create table if not exists launchGame (
gID varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerP (
gID varchar(100) not null,
aliasP varchar(100) not null,
    primary key (gID)
);

create table if not exists registerPlayerK (
gID varchar(100) not null,
aliasK varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedP (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists allDestroyedK (
gID varchar(100) not null,
allDestroyed varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreP (
gID varchar(100) not null,
scoreP varchar(100) not null,
    primary key (gID)
);

create table if not exists sendScoreK (
gID varchar(100) not null,
scoreK varchar(100) not null,
    primary key (gID)
);

create table if not exists stopP (
gID varchar(100) not null,
scoreK varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists stopK (
gID varchar(100) not null,
scoreP varchar(100) not null,
declared varchar(100) not null,
    primary key (gID)
);

create table if not exists gameOver (
gID varchar(100) not null,
scoreP varchar(100) not null,
scoreK varchar(100) not null,
outcome varchar(100) not null,
    primary key (gID)
);

create table if not exists fireP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
scoreP varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageP (
gID varchar(100) not null,
fID varchar(100) not null,
strengthP varchar(100) not null,
locationP varchar(100) not null,
damageP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultP (
gID varchar(100) not null,
fID varchar(100) not null,
damageP varchar(100) not null,
pointsP varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists fireK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
scoreK varchar(100),
allDestroyed varchar(100),
declared varchar(100),
    primary key (gID, fID)
);

create table if not exists damageK (
gID varchar(100) not null,
fID varchar(100) not null,
strengthK varchar(100) not null,
locationK varchar(100) not null,
damageK varchar(100) not null,
    primary key (gID, fID)
);

create table if not exists resultK (
gID varchar(100) not null,
fID varchar(100) not null,
damageK varchar(100) not null,
pointsK varchar(100) not null,
    primary key (gID, fID)
);
