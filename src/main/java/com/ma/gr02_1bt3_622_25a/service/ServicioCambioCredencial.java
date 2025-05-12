package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;

public class ServicioCambioCredencial {

    public void restablecerCredencial(HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        String password = (String) request.getAttribute("password");
        request.setAttribute("estado", "Token");
    }

}
