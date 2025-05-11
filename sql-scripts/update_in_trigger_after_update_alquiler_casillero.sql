create definer = root@`%` trigger after_update_alquiler_casillero
    after update
    on Alquiler_Casillero
    for each row
BEGIN
    -- Solo si el estado pasó a 'VENCIDO'
    IF (NEW.estado_alquiler = 'Vencido' OR NEW.estado_alquiler = 'Rechazado') AND (OLD.estado_alquiler != 'Vencido' AND OLD.estado_alquiler != 'Rechazado') THEN
        UPDATE Casillero
        SET estado = 'Disponible'
        WHERE id_casillero = NEW.id_casillero;
    END IF;

    IF NEW.estado_alquiler = 'Activo' AND OLD.estado_alquiler != 'Activo' THEN
        UPDATE Casillero
        SET estado = 'Ocupado'
        WHERE id_casillero = NEW.id_casillero;
    END IF;

    -- Si se cambió el id_casillero (reasignación)
    IF NEW.id_casillero <> OLD.id_casillero THEN
        -- Poner el casillero anterior como Disponible
        UPDATE Casillero
        SET estado = 'Disponible'
        WHERE id_casillero = OLD.id_casillero;

        -- Poner el nuevo casillero como Ocupado
        UPDATE Casillero
        SET estado = 'Ocupado'
        WHERE id_casillero = NEW.id_casillero;
    END IF;
END;

