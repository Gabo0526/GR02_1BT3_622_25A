package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public class ServicioMensajeria {
    private NotificacionService notificacionService;

    public ServicioMensajeria(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public void aprobarSolicitud(HttpServletRequest request) {
        AlquilerCasillero alquilerCasillero = (AlquilerCasillero) request.getAttribute("alquilerCasillero");
        request.setAttribute("estado", "aprobado");
        notificacionService.enviarAprobacion(alquilerCasillero.getIdUsuario(), alquilerCasillero.getIdCasillero());
    }

    public void rechazarSolicitud(HttpServletRequest request) {
        AlquilerCasillero alquilerCasillero = (AlquilerCasillero) request.getAttribute("alquilerCasillero");
        String motivo = (String) request.getAttribute("motivoRechazo");
        request.setAttribute("estado", "rechazado");
        notificacionService.enviarRechazo(alquilerCasillero.getIdUsuario(), motivo);
    }
    public void enviarEnlaceRecuperacion(HttpServletRequest request) {
        String token = (String) request.getAttribute("token");
        Usuario usuario = (Usuario) request.getAttribute("usuario");

        request.setAttribute("estado", "enlaceRecuperacion");

        notificacionService.enviarEnlaceRecuperacion(usuario, token);
    }

}
