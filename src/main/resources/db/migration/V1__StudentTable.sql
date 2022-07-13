create table if not exists students
(
   id uuid primary key not null,
   fullName varchar(100) not null,
   classNumber integer not null,
   email varchar(100) not null
);