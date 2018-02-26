create table parents as
  select "abraham" as parent, "barack" as child union
  select "abraham"          , "clinton"         union
  select "delano"           , "herbert"         union
  select "fillmore"         , "abraham"         union
  select "fillmore"         , "delano"          union
  select "fillmore"         , "grover"          union
  select "eisenhower"       , "fillmore";

create table dogs as
  select "abraham" as name, "long" as fur, 26 as height union
  select "barack"         , "short"      , 52           union
  select "clinton"        , "long"       , 47           union
  select "delano"         , "long"       , 46           union
  select "eisenhower"     , "short"      , 35           union
  select "fillmore"       , "curly"      , 32           union
  select "grover"         , "short"      , 28           union
  select "herbert"        , "curly"      , 31;

create table sizes as
  select "toy" as size, 24 as min, 28 as max union
  select "mini",        28,        35        union
  select "medium",      35,        45        union
  select "standard",    45,        60;

-------------------------------------------------------------
-- PLEASE DO NOT CHANGE ANY SQL STATEMENTS ABOVE THIS LINE --
-------------------------------------------------------------

-- The size of each dog
create table size_of_dogs as
  select a.name, b.size from dogs as a, sizes as b where a.height > b.min and a.height <= b.max;

-- All dogs with parents ordered by decreasing height of their parent
create table by_height as
  select child from parents as c, dogs as d where c.parent = d.name order by height desc;

-- Sentences about siblings that are the same size
create table sentences as
  with sizeC(child, parent, size) as (select a.child, a.parent, size from parents as a Inner join size_of_dogs as b on a.child = b.name)
  select  a.child || " and " || b.child || " are " || a.size || " siblings" from sizeC as a, sizeC as b
  where a.parent = b.parent and b.child > a.child and a.size = b.size;

-- Ways to stack 4 dogs to a height of at least 170, ordered by total height
create table stacks as
  with dog_stacks(dName, dNumber, dHeightTotes, last_dog_height) AS
  (select name, 1, height, height from dogs union
  select s.dName || ", " || d.name, dNumber + 1, dHeightTotes + d.height, d.height
  from dog_stacks as s, dogs as d where s.last_dog_height < d.height and dNumber < 4)
  select dName, dHeightTotes from dog_stacks where dHeightTotes >= 170 and dNumber = 4 order by dHeightTotes;

-- non_parents is an optional, but recommended question
-- All non-parent relations ordered by height difference
create table non_parents as
  select "replace";

create table ints as
    with i(n) as (
        select 1 union
        select n+1 from i limit 100
    )
    select n from i;

create table divisors as
    select a.n as Integers, sum(a.n / a.n) as Divisor from ints as a, ints as b where a.n % b.n = 0 group by a.n;

create table primes as
    select Integers from divisors where Divisor = 2;
