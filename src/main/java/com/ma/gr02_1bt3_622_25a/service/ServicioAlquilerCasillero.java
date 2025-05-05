package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

public class ServicioAlquilerCasillero {
    private final CasilleroDAO casilleroDAO = new CasilleroDAO();
    private final AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();

    public void registrarSolicitud(Usuario usuario, int idCasillero, double costo, String rutaComprobante) {
        Casillero casillero = casilleroDAO.find(idCasillero);

        AlquilerCasillero alquiler = new AlquilerCasillero();
        alquiler.setIdUsuario(usuario);
        alquiler.setIdCasillero(casillero);
        alquiler.setDetalleAlquiler(Map.of("costo", costo, "ruta", rutaComprobante));

        LocalDateTime fecha = LocalDateTime.of(2025, 8, 4, 0, 0);
        Instant vencimiento = fecha.atZone(ZoneId.of("America/Guayaquil")).toInstant();
        alquiler.setFechaVencimiento(vencimiento);

        alquilerCasilleroDAO.save(alquiler);
    }
}
