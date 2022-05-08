DROP DATABASE IF EXISTS `VideoLibrary`;
CREATE DATABASE `VideoLibrary`;

USE `VideoLibrary`;
/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE `Actors`
(
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(30),
    `Birth` DATE
);
CREATE TABLE `Films`
(
    `Id` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(30),
    `ReleaseDate` DATE,
    Country VARCHAR(30),
    `ActorId` INT, 
    FOREIGN KEY (ActorId) REFERENCES Actors(Id) ON DELETE CASCADE
);
CREATE TABLE `Directors`
(
    `DirectorId` INT AUTO_INCREMENT PRIMARY KEY,
    `DirectorName` VARCHAR(30),
    `DirectorBirth` DATE,
    `CreatedFilmName` VARCHAR(30)
);

insert into Actors (Name, Birth) values ("FirstActor","1988-11-11");
insert into Actors (Name, Birth) values ("SecondActor","1978-5-11");
insert into Actors (Name, Birth) values ("ThirdActor","1987-3-11");

insert into Films (Name, ReleaseDate, Country, ActorId) 
values ("Film1","2008-11-11","America", (select Id from Actors where name="FirstActor"));
insert into Films (Name, ReleaseDate, Country, ActorId) 
values ("Film1","2008-11-11","America", (select Id from Actors where name="SecondActor"));
insert into Films (Name, ReleaseDate, Country, ActorId) 
values ("Film2","2007-01-01","France", (select Id from Actors where name="FirstActor"));
insert into Films (Name, ReleaseDate, Country, ActorId) 
values ("Film2","2007-01-01","France", (select Id from Actors where name="ThirdActor"));
insert into Films (Name, ReleaseDate, Country, ActorId) 
values ("Film3","2010-11-21","Britain", (select Id from Actors where name="ThirdActor"));

insert into Directors (DirectorName, DirectorBirth, CreatedFilmName) values ("Director1","1988-11-11", "Film1");
insert into Directors (DirectorName, DirectorBirth, CreatedFilmName) values ("Director2","1948-1-2" , "Film2");
insert into Directors (DirectorName, DirectorBirth, CreatedFilmName) values ("Director1","1985-5-4" , "Film3");