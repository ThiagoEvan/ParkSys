CREATE DATABASE ParkSys;
USE ParkSys;

CREATE TABLE login(
	id int AUTO_INCREMENT PRIMARY KEY,
	usuario varchar(50) NOT NULL,
	senha varchar(50) NOT NULL,
	email varchar(80) NOT NULL
);

CREATE TABLE vagas(
	cupom int AUTO_INCREMENT PRIMARY KEY,
	placa varchar(10) NOT NULL,
	observação varchar(245) NOT NULL,
	hr_entrada datetime,
	hr_saida datetime
);

CREATE TABLE financeiro(
	id_pagamento int AUTO_INCREMENT PRIMARY KEY,
	tipo_pagamento varchar(30) NOT NULL,
	valor float NOT NULL,
	multiplicador float NOT NULL,
	cupom int,
    FOREIGN KEY (cupom) REFERENCES vagas(cupom)
);