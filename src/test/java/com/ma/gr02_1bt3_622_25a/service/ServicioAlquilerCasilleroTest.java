package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicioAlquilerCasilleroTest {

    @Test
    public void givenNull_whenProcessResults_thenReturnEmptyList() {
        // Arrange
        ServicioAlquilerCasillero servicio = new ServicioAlquilerCasillero();
        List<AlquilerCasillero> resultados = null;

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void givenEmptyList_whenProcessResults_thenReturnEmptyList() {
        // Arrange
        ServicioAlquilerCasillero servicio = new ServicioAlquilerCasillero();
        List<AlquilerCasillero> resultados = List.of();

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void givenNonEmptyList_whenProcessResults_thenReturnSameList() {
        // Arrange
        ServicioAlquilerCasillero servicio = new ServicioAlquilerCasillero();
        List<AlquilerCasillero> resultados = List.of(new AlquilerCasillero(), new AlquilerCasillero());

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertEquals(resultados, resultado);
    }
}