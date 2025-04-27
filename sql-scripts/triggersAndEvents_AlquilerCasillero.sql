DELIMITER //

CREATE TRIGGER before_insert_alquiler_casillero
BEFORE INSERT ON Alquiler_Casillero
FOR EACH ROW
BEGIN
    DECLARE estado_casillero VARCHAR(50);

    -- Consultar el estado del casillero
    SELECT estado INTO estado_casillero
    FROM Casillero
    WHERE id_casillero = NEW.id_casillero;

    -- Validar si no existe o no está disponible
    IF estado_casillero IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El casillero especificado no existe.';
    ELSEIF estado_casillero != 'Disponible' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El casillero no está disponible para alquiler.';
    END IF;
END;

//
DELIMITER ;

DELIMITER //

CREATE TRIGGER after_insert_alquiler_casillero
AFTER INSERT ON Alquiler_Casillero
FOR EACH ROW
BEGIN
    -- Actualizar el estado del casillero a 'Pendiente'
    UPDATE Casillero
    SET estado = 'Pendiente'
    WHERE id_casillero = NEW.id_casillero;
END;

//
DELIMITER ;

SET GLOBAL event_scheduler = ON;

DELIMITER //

CREATE EVENT actualizar_estado_alquiler
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    UPDATE Alquiler_Casillero
    SET estado_alquiler = 'Vencido'
    WHERE fecha_vencimiento <= CURRENT_DATE()
      AND estado_alquiler != 'Vencido';
END;

//
DELIMITER ;

DELIMITER //

CREATE TRIGGER after_update_alquiler_casillero
AFTER UPDATE ON Alquiler_Casillero
FOR EACH ROW
BEGIN
    -- Solo si el estado pasó a 'VENCIDO'
    IF NEW.estado_alquiler = 'Vencido' AND OLD.estado_alquiler != 'Vencido' THEN
        UPDATE Casillero
        SET estado = 'Disponible'
        WHERE id_casillero = NEW.id_casillero;
    END IF;
END;

//
DELIMITER ;