package com.ma.gr02_1bt3_622_25a.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidar la sesión actual si existe
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Crear una nueva sesión vacía
        request.getSession(true);

        // Redirigir al inicio
        response.sendRedirect("index.jsp");
    }
}
