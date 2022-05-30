DROP DATABASE IF EXISTS `Conferences`;
CREATE DATABASE `conferences`;

USE `Conferences`;
/*******************************************************************************
   Create Tables
********************************************************************************/
create table user_table
(
    user_id      int auto_increment,
    user_pass    varchar(30) not null,
    user_login   varchar(30) not null,
    user_email   varchar(30) null,
    user_name    varchar(30) null,
    user_surname varchar(30) null,
    user_role    varchar(30) not null,
    constraint user_table_pk
        primary key (user_id)
);

create unique index user_table_user_id_uindex
    on user_table (user_id);

