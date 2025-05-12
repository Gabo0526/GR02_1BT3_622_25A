package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CasilleroDAOTest {
    CasilleroDAO casilleroDAO = new CasilleroDAO();
    static AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();

    @ParameterizedTest
    @MethodSource("provideUpdateLockerStateParametersGivenAlquilerCasillero")
    public void givenAlquilerCasillero_whenUpdateLockerState_thenCheckState(AlquilerCasillero alquilerCasillero, String nuevoEstado) {
        boolean resultado = casilleroDAO.actualizarEstadoCasilleroDadoAlquiler(alquilerCasillero, nuevoEstado);
        Casillero casillero = casilleroDAO.find(alquilerCasillero.getIdCasillero().getId());
        String estadoActual = casillero.getEstado();

        assertTrue(resultado);
        assertEquals(nuevoEstado, estadoActual);
    }

    public static Stream<Arguments> provideUpdateLockerStateParametersGivenAlquilerCasillero() {
        return Stream.of(
                Arguments.of(alquilerCasilleroDAO.find(19), "Averiado"),
                Arguments.of(alquilerCasilleroDAO.find(3), "Averiado"),
                Arguments.of(alquilerCasilleroDAO.find(19), "Ocupado"),
                Arguments.of(alquilerCasilleroDAO.find(3), "Ocupado"));
    }
}