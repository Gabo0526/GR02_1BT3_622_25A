package com.ma.gr02_1bt3_622_25a.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ServicioVerCasillerosTest {
    @Test
    public void givenNoViewMode_whenGetViewMode_thenReturnReservarViewMode() {
        // Arrange
        ServicioVerCasilleros servicio = new ServicioVerCasilleros();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getAttribute("modo")).thenReturn(null);
        String expectedViewMode = "reservar";

        // Act
        String actualViewMode = servicio.getViewMode(request);

        // Assert
        assertEquals(expectedViewMode, actualViewMode);
    }

    @Test
    public void givenViewMode_whenGetViewMode_thenReturnViewMode() {
        // Arrange
        ServicioVerCasilleros servicio = new ServicioVerCasilleros();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getAttribute("modo")).thenReturn("someViewMode");
        String expectedViewMode = "someViewMode";

        // Act
        String actualViewMode = servicio.getViewMode(request);

        // Assert
        assertEquals(expectedViewMode, actualViewMode);
    }
}