package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicioAlquilerCasilleroTest {
    static ServicioAlquilerCasillero servicio;

    @BeforeAll
    public static void setUp() {
        servicio = new ServicioAlquilerCasillero();
    }

    @AfterAll
    public static void tearDown() {
        servicio = null;
    }

    @Test
    public void givenNull_whenProcessResults_thenReturnEmptyList() {
        // Arrange
        List<AlquilerCasillero> resultados = null;

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void givenEmptyList_whenProcessResults_thenReturnEmptyList() {
        // Arrange
        List<AlquilerCasillero> resultados = List.of();

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void givenNonEmptyList_whenProcessResults_thenReturnSameList() {
        // Arrange
        List<AlquilerCasillero> resultados = List.of(new AlquilerCasillero(), new AlquilerCasillero());

        // Act
        List<AlquilerCasillero> resultado = servicio.procesarResultadosConsulta(resultados);

        // Assert
        assertEquals(resultados, resultado);
    }
}