package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("email");
        String clave = request.getParameter("password");
        String rol = request.getParameter("rol");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(correo, clave, rol);

        if (usuario != null) {
            // Guardar el usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
