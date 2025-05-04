package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebServlet(name = "ActualizarSolicitudServlet", urlPatterns = {"/ActualizarSolicitudServlet"})
public class ActualizarSolicitudServlet extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException {
        String idSolicitud = request.getParameter("idSolicitud");
        String accion = request.getParameter("accion");

        AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();

        if (accion.equals("aprobar")) {
            alquilerCasilleroDAO.actualizar(Integer.parseInt(idSolicitud), "Activo");
        } else if (accion.equals("rechazar")) {
            alquilerCasilleroDAO.actualizar(Integer.parseInt(idSolicitud), "Rechazado");
        }

        response.sendRedirect("SolicitudServlet");
    }
}
