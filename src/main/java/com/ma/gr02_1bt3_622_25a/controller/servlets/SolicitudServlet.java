package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
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

@WebServlet(name = "SolicitudServlet", urlPatterns = {"/SolicitudServlet"})
public class SolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario idusuario = (Usuario) request.getSession().getAttribute("usuario");
        Casillero idcasillero = (Casillero) request.getSession().getAttribute("casillero");
        String precio = request.getParameter("precio");

        if (precio == null || precio.isEmpty()) {
            response.sendRedirect("solicitudServlet.jsp");
            return;
        }

        AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();
        AlquilerCasillero alquilerCasillero = new AlquilerCasillero();

        alquilerCasillero.setIdUsuario(idusuario);
        alquilerCasillero.setIdCasillero(idcasillero);
        System.out.println(alquilerCasillero);

        alquilerCasilleroDAO.save(alquilerCasillero);

        response.sendRedirect("index.jsp");
    }
}
