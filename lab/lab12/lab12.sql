.read sp16data.sql
.read fa16data.sql

CREATE TABLE obedience AS
  select seven, denero FROM students;

CREATE TABLE smallest_int AS
  select time, smallest
  FROM students
  WHERE students.smallest > 8
  ORDER BY smallest ASC
  LIMIT 20;

CREATE TABLE greatstudents AS
  select fa.date, fa.number, fa.pet, fa.color, sp.color
  FROM students AS fa, sp16students as sp
  WHERE fa.date = sp.date AND fa.number = sp.number AND fa.pet = sp.pet;

CREATE TABLE sevens AS
  select s.seven
  FROM students as s, checkboxes as c
  WHERE s.time = c.time AND s.number = 7 and c.'7' = 'True';

CREATE TABLE matchmaker AS
  select s1.pet, s1.song, s1.color, s2.color
  FROM students as s1, students as s2
  WHERE s1.time < s2.time AND s1.pet = s2.pet AND s1.song = s2.song;
