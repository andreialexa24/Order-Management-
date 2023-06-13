DROP SCHEMA IF EXISTS orders_management;
CREATE DATABASE IF NOT EXISTS orders_management;

use orders_management;

CREATE TABLE client(
  idClient int NOT NULL AUTO_INCREMENT primary key,
  Nume varchar(45) DEFAULT NULL,
  Prenume varchar(45) DEFAULT NULL,
  Adresa varchar(45) DEFAULT NULL,
  Email varchar(45) DEFAULT NULL,
  age int NOT NULL
);

CREATE TABLE produs (
  idProdus INT NOT NULL AUTO_INCREMENT primary key,
  Nume VARCHAR(45) NULL,
  Stoc INT UNSIGNED NULL,
  Pret FLOAT UNSIGNED NULL
  );

CREATE TABLE Comanda (
  idComanda INT NOT NULL ,
  Produs_Id INT NOT NULL,
  Nume VARCHAR(45) NULL,
  Client_Id INT NOT NULL,
  Cantitate INT UNSIGNED NOT NULL,
  Pret FLOAT not null,
  INDEX Client_Id_idx (Client_Id ASC) , #daca sterg clientul, sterg toate comenzile clientului
  INDEX P_idx (Produs_Id ASC) ,
  CONSTRAINT Client_Id
    FOREIGN KEY (Client_Id)
    REFERENCES client (idClient)
    ON DELETE CASCADE,
  CONSTRAINT Produs_Id
    FOREIGN KEY (Produs_Id)
    REFERENCES produs (idProdus));