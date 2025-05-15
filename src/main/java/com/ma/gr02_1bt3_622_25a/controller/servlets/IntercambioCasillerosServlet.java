package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.service.ServicioAlquilerCasillero;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "IntercambioCasillerosServlet", urlPatterns = {"/IntercambioCasillerosServlet"})
public class IntercambioCasillerosServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        int idCasillero = Integer.parseInt(request.getParameter("casilleroId"));
        int idAlquilerActivo = Integer.parseInt(request.getParameter("idAlquilerActivo"));

        AlquilerCasillero alquiler = new AlquilerCasillero();
        alquiler.setIdUsuario(usuario);
        alquiler.setDetalleAlquiler(Map.of("intercambio", idAlquilerActivo));

        new ServicioAlquilerCasillero().registrarSolicitud(alquiler, idCasillero);

        response.sendRedirect("home.jsp");
    }
}
