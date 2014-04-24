CREATE TABLE visitor (
id varchar(20),
visitor_id varchar(20),
nickname varchar(30)
);

INSERT INTO visitor VALUES ('minslovey', 'mathical', 'so~cool');
INSERT INTO visitor VALUES ('minslovey', 'minslovey', 'yomanman');
INSERT INTO visitor VALUES ('minslovey', 'whoareu', 'im not a girl');
INSERT INTO visitor VALUES ('minslovey', 'thanks', 'PPPPoooPPPP');
INSERT INTO visitor VALUES ('minslovey', 'humanp', 'humanp');

CREATE TABLE article (
id varchar(20),
title varchar(50),
contents varchar(30),
createdate date,
hit int
);

INSERT INTO article VALUES ('minslovey', 'my blog good0', 'abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good1', 'contents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contentscontents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good2', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good3', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good4', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good5', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good6', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good7', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good8', 'contents * 999 * contents', now(), 1234);
INSERT INTO article VALUES ('minslovey', 'my blog good9', 'abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br>abcabcabcabcabcabcabcabcabcabcabcab<br> * contents', now(), 1234);

CREATE TABLE profile (
id varchar(20),
name varchar(30),
age int,
hobby varchar(50)
);

INSERT INTO profile VALUES ('minslovey', 'Min Cha', 32, 'Reading book');

CREATE TABLE comment (
id varchar(20),
comment varchar(50),
createdate date
);

INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents1!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents2!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents3!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents4!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents5!', now());
INSERT INTO comment VALUES ('minslovey', 'good contents6!', now());