package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioMensajeriaTest {
    @Test
    void givenAlquilerCasillero_whenIsApproved_thenNotify() {
        // Arrange
        NotificacionService notificacionService = Mockito.mock(NotificacionService.class);
        ServicioMensajeria servicio = new ServicioMensajeria(notificacionService);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        AlquilerCasillero alquilerCasillero = new AlquilerCasillero();

        Mockito.when(request.getAttribute("alquilerCasillero")).thenReturn(alquilerCasillero);

        // Act
        servicio.aprobarSolicitud(request);

        // Assert
        Mockito.verify(request).setAttribute("estado", "aprobado");
        Mockito.verify(notificacionService).enviarAprobacion(alquilerCasillero.getIdUsuario(), alquilerCasillero.getIdCasillero());
    }

    @Test
    void givenAlquilerCasillero_whenNotApproved_thenNotify() {
        // Arrange
        NotificacionService notificacionService = Mockito.mock(NotificacionService.class);
        ServicioMensajeria servicio = new ServicioMensajeria(notificacionService);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        AlquilerCasillero alquilerCasillero = new AlquilerCasillero();
        String motivo = "No cumple requisitos";

        Mockito.when(request.getAttribute("alquilerCasillero")).thenReturn(alquilerCasillero);
        Mockito.when(request.getAttribute("motivoRechazo")).thenReturn(motivo);

        // Act
        servicio.rechazarSolicitud(request);

        // Assert
        Mockito.verify(request).setAttribute("estado", "rechazado");
        Mockito.verify(notificacionService).enviarRechazo(alquilerCasillero.getIdUsuario(), motivo);
    }

    @Test
    void givenRecoveryToken_whenSendTryEnlace_thenCallEmailService() {
        // Arrange
        NotificacionService notificacionService = Mockito.mock(NotificacionService.class);
        ServicioMensajeria servicio = new ServicioMensajeria(notificacionService);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Usuario usuario = new Usuario();

        String token = "<PASSWORD>";

        Mockito.when(request.getAttribute("token")).thenReturn(token);
        Mockito.when(request.getAttribute("usuario")).thenReturn(usuario);

        // Act
        servicio.enviarEnlaceRecuperacion(request);

        // Assert
        Mockito.verify(request).setAttribute("estado", "enlaceRecuperacion");
        Mockito.verify(notificacionService).enviarEnlaceRecuperacion(usuario, token);
    }
}