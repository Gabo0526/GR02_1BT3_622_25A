DELIMITER //

CREATE TRIGGER after_insert_usuario
AFTER INSERT ON Usuario
FOR EACH ROW
BEGIN
    INSERT INTO Usuario_Rol (id_usuario, id_rol)
    VALUES (NEW.cedula, 1);
END;

//
DELIMITER ;
