create database manufactura;

use  manufactura;

CREATE TABLE Defectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroLote VARCHAR(255) NOT NULL,
    detalles VARCHAR(255) NOT NULL,
    defectos VARCHAR(255) NOT NULL
);


select * from Defectos;
