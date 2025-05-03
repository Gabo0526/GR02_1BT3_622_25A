package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SolicitudServlet", urlPatterns = {"/SolicitudServlet"})
public class SolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String precio = request.getParameter("precio");

        if (precio == null || precio.isEmpty()) {
            response.sendRedirect("home.jsp");
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        int idCasillero = Integer.parseInt(request.getParameter("casilleroId"));
        String ruta = (String) request.getAttribute("ruta");
        double costo = Double.parseDouble(precio);

        CasilleroDAO casilleroDAO = new CasilleroDAO();
        Casillero casillero = casilleroDAO.find(idCasillero);

        AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();
        AlquilerCasillero alquilerCasillero = new AlquilerCasillero();

        ZoneId zonaEcuador = ZoneId.of("America/Guayaquil");
        LocalDateTime fechaLocal = LocalDateTime.of(2025, 8, 4, 0, 0);
        Instant instant = fechaLocal.atZone(zonaEcuador).toInstant();

        Map<String, Object> detalles = new HashMap<>();
        detalles.put("ruta", ruta);
        detalles.put("costo", costo);

        alquilerCasillero.setIdUsuario(usuario);
        alquilerCasillero.setIdCasillero(casillero);
        alquilerCasillero.setFechaVencimiento(instant);
        alquilerCasillero.setDetalleAlquiler(detalles);

        alquilerCasilleroDAO.save(alquilerCasillero);

        response.sendRedirect("home.jsp");
    }
}
