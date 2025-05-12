package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioCambioCredencialTest {
    @Test
    void givenSolicitude_whenRestorePassword_thenGenerateToken() {
        // Arrange
        ServicioCambioCredencial servicio = new ServicioCambioCredencial();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Usuario usuario = new Usuario();

        String password = "credential";

        Mockito.when(request.getAttribute("password")).thenReturn(password);

        // Act
        servicio.restablecerCredencial(request);

        // Assert
        Mockito.verify(request).setAttribute("estado", "Token");
    }
}