package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import org.hibernate.exception.GenericJDBCException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlquilerCasilleroDAOTest {
    AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();
    static CasilleroDAO casilleroDAO = new CasilleroDAO();

    /*@ParameterizedTest
    @MethodSource("provideLockerExchangeParameters")
    public void givenMethodParameters_whenLockerExchange_thenVerifyLockersStatusChange(Casillero nuevoCasillero, int idAlquiler) {
        // Este test unitario verifica el cambio de estados que se produce al intercambiar un casillero. Este cambio de estados deberia ser manejado
        // por la base de datos.
        AlquilerCasillero alquilerCasillero = alquilerCasilleroDAO.find(idAlquiler);

        boolean success = alquilerCasilleroDAO.intercambiarCasillero(nuevoCasillero, idAlquiler);
        assertTrue(success);

        String estadoActualNuevoCasillero = casilleroDAO.find(nuevoCasillero.getId()).getEstado();
        assertEquals("Ocupado", estadoActualNuevoCasillero);

        String estadoActualViejoCasillero = casilleroDAO.find(alquilerCasillero.getIdCasillero().getId()).getEstado();
        assertEquals("Disponible", estadoActualViejoCasillero);
    }

    public static Stream<Arguments> provideLockerExchangeParameters() {
        // Estos parametros dejan a la base de datos en el mismo estado en el que estaba antes de la prueba.
        return Stream.of(
                Arguments.of(casilleroDAO.find(4), 19),
                Arguments.of(casilleroDAO.find(2), 19),
                Arguments.of(casilleroDAO.find(44), 3),
                Arguments.of(casilleroDAO.find(42), 3));
    }

    @Test
    public void givenInvalidParameters_whenLockerExchange_thenCheckException() {
        Casillero casillero = casilleroDAO.find(3);
        int idAlquiler = 19;

        GenericJDBCException exception = assertThrows(GenericJDBCException.class, () -> alquilerCasilleroDAO.intercambiarCasillero(casillero, idAlquiler));
        assertTrue(exception.getMessage().contains("No se puede asignar el nuevo casillero porque no está disponible."));
    }*/


}