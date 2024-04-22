create database manufactura;

use  manufactura;

CREATE TABLE Pieza (
    id INT auto_increment primary key,
    nombrePieza varchar(255) not null,
    precio int not null
);

CREATE TABLE Defectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroLote VARCHAR(255) NOT NULL,
    detalles VARCHAR(255) NOT NULL,
    defectos VARCHAR(255) NOT NULL,
    id_pieza INT,
    FOREIGN KEY (id_pieza) REFERENCES Pieza(id)
);

#select * from Pieza;
#select * from Defectos;

INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza A', 100);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza B', 150);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza C', 200);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza D', 250);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza E', 300);

INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote1', 'Rasguño superficial', 'Pieza Rota', 1);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote2', 'Desalineación leve', 'Fuga de Liquido', 2);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote3', 'Fisura', 'Tamanio Incorrecto', 3);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote4', 'Color incorrecto', 'Falta de Etiqueta', 4);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote5', 'Color incorrecto', 'Color incorrecto', 4);

