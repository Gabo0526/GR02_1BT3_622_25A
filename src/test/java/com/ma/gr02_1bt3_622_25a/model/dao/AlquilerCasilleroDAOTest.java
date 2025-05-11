package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import org.hibernate.exception.GenericJDBCException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlquilerCasilleroDAOTest {
    AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();
    static CasilleroDAO casilleroDAO = new CasilleroDAO();

    @ParameterizedTest
    @MethodSource("provideLockerExchangeParameters")
    public void givenMethodParameters_whenLockerExchange_thenVerify(Casillero nuevoCasillero, int idAlquiler, Object expected) {
        if (expected instanceof Class<?>) {
            GenericJDBCException exception = assertThrows(GenericJDBCException.class, () -> alquilerCasilleroDAO.intercambiarCasillero(nuevoCasillero, idAlquiler));
            assertTrue(exception.getMessage().contains("No se puede asignar el nuevo casillero porque no está disponible."));
        } else {
            boolean actual = alquilerCasilleroDAO.intercambiarCasillero(nuevoCasillero, idAlquiler);
            assertEquals(expected, actual);
        }
    }

    public static Stream<Arguments> provideLockerExchangeParameters() {
        return Stream.of(
                Arguments.of(casilleroDAO.find(3), 19, GenericJDBCException.class));
    }
}