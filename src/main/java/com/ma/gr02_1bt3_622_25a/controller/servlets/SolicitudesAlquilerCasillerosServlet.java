package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.service.ServicioAlmacenamientoArchivo;
import com.ma.gr02_1bt3_622_25a.service.ServicioAlquilerCasillero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SolicitudServlet", urlPatterns = {"/SolicitudServlet"})
@MultipartConfig
public class SolicitudesAlquilerCasillerosServlet extends HttpServlet {

    private final ServicioAlmacenamientoArchivo servicioArchivo = new ServicioAlmacenamientoArchivo();
    private final ServicioAlquilerCasillero servicioAlquiler = new ServicioAlquilerCasillero();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        router(request, response, request.getSession().getAttribute("rolUsuario").toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlquilerCasilleroDAO alquilerDAO = new AlquilerCasilleroDAO();
        List<AlquilerCasillero> solicitudes = alquilerDAO.findAll();

        request.setAttribute("alquilerCasilleros", solicitudes);
        request.getRequestDispatcher("admin/viewLockerRequests.jsp").forward(request, response);
    }

    private void router(HttpServletRequest request, HttpServletResponse response, String role)
            throws IOException {
        if (role.equals("Administrador")) {
            String idSolicitud = request.getParameter("idSolicitud");
            String accion = request.getParameter("accion");

            AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();

            if (accion.equals("aprobar")) {
                alquilerCasilleroDAO.actualizarAlquilerCasillero(Integer.parseInt(idSolicitud), "Activo");
            } else if (accion.equals("rechazar")) {
                alquilerCasilleroDAO.actualizarAlquilerCasillero(Integer.parseInt(idSolicitud), "Rechazado");
            }

            response.sendRedirect("SolicitudServlet");

        } else if (role.equals("Estudiante")) {
            try {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                int idCasillero = Integer.parseInt(request.getParameter("casilleroId"));
                double costo = Double.parseDouble(request.getParameter("precio"));
                Part imagen = request.getPart("imagen");

                String rutaComprobante = servicioArchivo.guardarImagen(imagen);
                servicioAlquiler.registrarSolicitud(usuario, idCasillero, costo, rutaComprobante);

                response.sendRedirect("CasilleroServlet?idBloque=" + request.getParameter("numeroBloque"));

            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el procesamiento de la solicitud.");
            }
        }
    }
}