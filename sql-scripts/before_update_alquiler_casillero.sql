DELIMITER $$

CREATE TRIGGER before_update_alquiler_casillero
BEFORE UPDATE ON Alquiler_Casillero
FOR EACH ROW
BEGIN
    -- Verificar si se está intentando cambiar el id_casillero
    IF NEW.id_casillero <> OLD.id_casillero THEN
        -- Verificar si el nuevo casillero está disponible
        IF NOT EXISTS (
            SELECT 1
            FROM Casillero
            WHERE id_casillero = NEW.id_casillero
              AND estado = 'Disponible'
        ) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede asignar el nuevo casillero porque no está disponible.';
        END IF;
    END IF;
END$$

DELIMITER ;
