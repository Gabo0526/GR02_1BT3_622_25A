package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;

public interface NotificacionService {
    void enviarAprobacion(Usuario usuario, Casillero casillero);
    void enviarRechazo(Usuario usuario, String motivo);
}
