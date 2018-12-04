CREATE VIEW view1 AS SELECT m1.k1, m1.p1 FROM m1;

CREATE VIEW view2 AS SELECT m2.k1, view1.p1 FROM m2 LEFT JOIN view1 ON m2.k1 = view1.k1 UNION SELECT view1.k1, view1.p1 FROM view1 WHERE (view1.k1) NOT IN (SELECT m2.k1 FROM m2);

CREATE VIEW view3 AS SELECT view2.k1, view2.p1, m2.p2 FROM view2 LEFT JOIN m2 ON view2.k1 = m2.k1;

CREATE VIEW view4 AS SELECT m3.k1, view3.p1, m3.p2 FROM m3 LEFT JOIN view3 ON m3.k1 = view3.k1 UNION SELECT view3.k1, view3.p1, view3.p2 FROM view3 WHERE (view3.k1) NOT IN (SELECT m3.k1 FROM m3);

CREATE VIEW view5 AS SELECT view4.k1, view4.p1, view4.p2, m3.p3 FROM view4 LEFT JOIN m3 ON view4.k1 = m3.k1;

CREATE VIEW view6 AS SELECT m4.k1, view5.p1, view5.p2, view5.p3 FROM m4 LEFT JOIN view5 ON m4.k1 = view5.k1 UNION SELECT view5.k1, view5.p1, view5.p2, view5.p3 FROM view5 WHERE (view5.k1) NOT IN (SELECT m4.k1 FROM m4);

CREATE VIEW view7 AS SELECT view6.k1, view6.p1, view6.p2, view6.p3, m4.p4 FROM view6 LEFT JOIN m4 ON view6.k1 = m4.k1;

CREATE VIEW view8 AS SELECT m5.k1, view7.p1, view7.p2, m5.p3, view7.p4 FROM m5 LEFT JOIN view7 ON m5.k1 = view7.k1 UNION SELECT view7.k1, view7.p1, view7.p2, view7.p3, view7.p4 FROM view7 WHERE (view7.k1) NOT IN (SELECT m5.k1 FROM m5);

CREATE VIEW view9 AS SELECT view8.k1, view8.p1, view8.p2, view8.p3, view8.p4, m5.p5 FROM view8 LEFT JOIN m5 ON view8.k1 = m5.k1;

SELECT view9.k1, view9.p1, view9.p2, view9.p3, view9.p4, view9.p5 FROM view9 ORDER BY k1+"0";