set names utf8mb4;
set charset utf8mb4;
drop database if exists huisbieder;
create database huisbieder charset utf8mb4;
use huisbieder;

create table gemeenten (
  id bigint(20) unsigned not null auto_increment,
  postcode varchar(6) not null,
  naam varchar(50) not null,
  primary key (id)
);

create table gebruikers (
  id bigint(20) unsigned not null auto_increment,
  voornaam varchar(50) not null,
  familienaam varchar(50) not null,
  straatnaam varchar(50) not null,
  huisnummer varchar(50) not null,
  gemeenteId bigint(20) unsigned not null,
  telefoonnummer varchar(16) default null,
  email varchar(45) not null,
  gebruikersnaam varchar(12) not null unique,
  paswoord varchar(100) not null,
  primary key (id),
  -- key fk_gebruikers_gemeenten_id (gemeenteId),
  constraint fk_gebruikers_gemeenten foreign key (gemeenteId) references gemeenten (id)
  );

create table rollen (
  id bigint(20) unsigned not null auto_increment,
  naam varchar(45) not null,
  primary key (id),
  unique key naam_UNIQUE (naam)
);

create table gebruikersrollen (
  gebruikerId bigint(20) unsigned not null,
  rolId bigint(20) unsigned not null,
  primary key (gebruikerId),
  key fk_gebruikersrollen_rollen_id (rolId),
  constraint fk_gebruikersrollen_gebruikers foreign key (gebruikerId) references gebruikers (id),
  constraint fk_gebruikersrollen_rollen foreign key (rolId) references rollen (id)
);

create table panden (
  id bigint(20) unsigned not null auto_increment,
  datumToegevoegd datetime not null,
--   typeWoning enum('Appartement', 'Commercieel', 'Garage', 'Huis', 'Industrie', 'Kantoor', 'Grond', 'Andere'),
  typeWoning tinyint(1) unsigned not null,
  naam varchar(50) not null,
  titel varchar(100) not null,
  straatnaam varchar(50) not null,
  huisnummer varchar(10) not null,
  gemeenteId bigint(20) unsigned not null,
  bouwjaar int unsigned,
  oppervlakte int unsigned,
  staatGebouw varchar(40),
  aantalgevels tinyint(1) unsigned,
  aantalslaapkamers tinyint(1) unsigned,
  aantalbadkamers tinyint(1) unsigned,
  aantaltoiletten tinyint(1) unsigned,
  aantalverdiepingen tinyint(1) unsigned,
  living tinyint(1) unsigned,
  eetkamer tinyint(1) unsigned,
  bureau tinyint(1) unsigned,
  kelder tinyint(1) unsigned,
  zolder tinyint(1) unsigned,
  garage tinyint(1) unsigned,
  tuin tinyint(1) unsigned,
  terras tinyint(1) unsigned,
  veranda tinyint(1) unsigned,
  code_epc varchar(40),
  dubbelebegelazing tinyint(1) unsigned,
  kadastraalinkomen int unsigned,
  vrijVanaf varchar(40),
  startbod int unsigned not null,
  huidigbod int unsigned,
  gebruikerId bigint(20) unsigned null,
  startdatum datetime not null,
  einddatum datetime not null,
  omschrijving varchar(2500),
  primary key (id),
--   key fk_panden_gemeenten_id (gemeenteId),
--   constraint fk_panden_gebruikers foreign key (gebruikerId) references gebruikers (id),
  constraint fk_panden_gemeenten foreign key (gemeenteId) references gemeenten (id)
);

create user if not exists application identified by 'application';
grant select, insert on gemeenten to application;
grant select on rollen to application;
grant select, insert on gebruikersrollen to application;
grant select, insert on gebruikers to application;
grant select, insert, update on panden to application;

