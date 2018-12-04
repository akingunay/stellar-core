drop database if exists n1;
create database n1;
use n1;

create table if not exists ma (
    ka varchar(40) not null,
    pa varchar(40),
	pb varchar(40),
    pc varchar(40),
    primary key (ka)
);

create table if not exists mb (
    ka varchar(40) not null,
    pa varchar(40),
	pb varchar(40),
    pd varchar(40),
    primary key (ka)
);

insert into ma (ka, pa, pb, pc) values
	("ka-1", "pa-1", "pb-1", "pc-1"),
	("ka-2", "pa-2", "pb-2", "pc-2");
    
insert into mb (ka, pa, pb, pd) values
	("ka-3", "pa-3", "pb-3", "pd-3"),
	("ka-4", "pa-4", "pb-4", "pd-4");
    
CREATE VIEW view1 AS SELECT ma.ka, ma.pa, ma.pb, ma.pc FROM ma;

CREATE VIEW view2 AS SELECT mb.ka, mb.pa, mb.pb, view1.pc FROM mb LEFT JOIN view1 ON mb.ka = view1.ka
UNION
SELECT view1.ka, view1.pa, view1.pb, view1.pc FROM view1 WHERE (view1.ka) NOT IN (SELECT mb.ka FROM mb);

CREATE VIEW view2 AS SELECT mb.ka, mb.pa, mb.pb, view1.pc FROM mb LEFT JOIN view1 ON mb.ka = view1.ka
UNION
SELECT view1.ka, view1.pa, view1.pb, view1.pc FROM view1 WHERE (view1.ka) NOT IN (SELECT mb.ka FROM mb);

CREATE VIEW view3 AS SELECT view2.ka, view2.pa, view2.pb, view2.pc, mb.pd FROM view2 LEFT JOIN mb ON view2.ka = mb.ka;

SELECT view3.ka, view3.pa, view3.pb, view3.pc, view3.pd FROM view3;
