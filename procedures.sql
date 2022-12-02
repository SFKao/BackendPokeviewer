USE Pokeviewer;

DELIMITER $$
CREATE PROCEDURE insertarEquipo(IN id CHAR(8),IN nombre VARCHAR, IN username VARCHAR, IN pk1 INT, IN pk2 INT, IN pk3 INT,
IN pk4 INT, IN pk5 INT, IN pk6 INT)
lastID VARCHAR
BEGIN
    INSERT INTO Equipo (id, nombre, usernameAutor, fecha, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6)
        VALUES (id, nombre, username, CURRENT_DATE,pk1,pk2,pk3,pk4,pk5,pk6);
END$$