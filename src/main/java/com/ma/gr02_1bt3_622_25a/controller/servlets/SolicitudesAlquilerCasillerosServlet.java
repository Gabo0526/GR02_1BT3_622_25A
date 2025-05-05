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

        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            int idCasillero = Integer.parseInt(request.getParameter("casilleroId"));
            double costo = Double.parseDouble(request.getParameter("precio"));
            Part imagen = request.getPart("imagen");

            String rutaComprobante = servicioArchivo.guardarImagen(imagen);
            servicioAlquiler.registrarSolicitud(usuario, idCasillero, costo, rutaComprobante);

            response.sendRedirect("home.jsp");

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el procesamiento de la solicitud.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlquilerCasilleroDAO alquilerDAO = new AlquilerCasilleroDAO();
        List<AlquilerCasillero> solicitudes = alquilerDAO.findAll();

        request.getSession().setAttribute("alquilerCasilleros", solicitudes);
        request.setAttribute("alquilerCasilleros", solicitudes);
        request.getRequestDispatcher("admin/viewLockerRequests.jsp").forward(request, response);
    }
}