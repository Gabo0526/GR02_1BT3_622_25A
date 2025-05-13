package com.ma.gr02_1bt3_622_25a.service;

import jakarta.servlet.http.HttpServletRequest;

public class ServicioVerCasilleros {

    public String getViewMode(HttpServletRequest request) {
        String viewMode = (String) request.getParameter("modo");
        if (viewMode == null) {
            return "reservar";
        }
        return viewMode;
    }
}
