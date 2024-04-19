create database manufactura;
# drop database manufactura;
use  manufactura;

CREATE TABLE Defectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeroLote VARCHAR(255) NOT NULL,
    detalles VARCHAR(255) NOT NULL,
    defectos VARCHAR(255) NOT NULL,
    id_pieza INT,
    FOREIGN KEY (id_pieza) REFERENCES Pieza(id)
);

CREATE TABLE Pieza (
    id INT auto_increment primary key,
    nombrePieza varchar(255) not null,
    precio int not null
);

select * from Pieza;
select * from Defectos;

INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza A', 100);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza B', 150);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza C', 200);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza D', 250);
INSERT INTO Pieza (nombrePieza, precio) VALUES ('Pieza E', 300);


INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote1', 'Rasguño superficial', 'Estético', 1);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote2', 'Desalineación leve', 'Funcional', 2);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote3', 'Fisura', 'Crítico', 3);
INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote4', 'Color incorrecto', 'Estético', 4);

INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES ('Lote5', 'Color incorrecto', 'Estético', 4);

#SELECT d.defectos AS TipoDeDefecto, SUM(p.precio) AS TotalCosto
#FROM Defectos d
#JOIN Pieza p ON d.id_pieza = p.id
#WHERE d.defectos = 'Estético'
#GROUP BY d.defectos;

#SELECT defectos AS TipoDeDefecto, COUNT(*) AS TotalPiezasRechazadas
#FROM Defectos d
#WHERE d.defectos = 'Estetico'
#GROUP BY defectos;

#SELECT 
#    d.numeroLote AS NumeroLote,
#    d.detalles AS DetallesDefecto,
#    d.defectos AS TipoDefecto,
#    p.nombrePieza AS NombrePieza,
#    p.precio AS PrecioPieza
#FROM Defectos d
#JOIN Pieza p ON d.id_pieza = p.id;
