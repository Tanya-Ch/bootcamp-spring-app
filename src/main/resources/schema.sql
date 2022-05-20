create table if not exists queens(
id        serial              not null,
coord     varchar(50)         not null,
value     int                 not null,
primary key (id)
);