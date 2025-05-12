package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MisCasillerosServlet", urlPatterns = {"/MisCasillerosServlet"})
public class MisCasillerosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<AlquilerCasillero> alquileres = new AlquilerCasilleroDAO().obtenerAlquileresPorUsuario((Usuario) session.getAttribute("usuario"));

        request.setAttribute("alquileres", alquileres);

        request.getRequestDispatcher("student/viewMyLockers.jsp").forward(request, response);
    }
}
