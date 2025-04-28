package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.BloqueCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
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
import java.util.List;

@WebServlet(name = "CasilleroServlet", urlPatterns = {"/CasilleroServlet"})
public class CasilleroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idBloque = request.getParameter("idBloque");

        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        BloqueCasillero bloqueCasillero = bloqueCasilleroDAO.find(Integer.parseInt(idBloque));

        CasilleroDAO casilleroDAO = new CasilleroDAO();
        List<Casillero> casilleros = casilleroDAO.obtenerPorBloque(bloqueCasillero);

        for (Casillero casillero : casilleros) {
            System.out.println(casillero);
        }

        HttpSession session = request.getSession();
        session.setAttribute("casilleros", casilleros);
        session.setAttribute("bloque", bloqueCasillero);

        request.getRequestDispatcher("verCasilleros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }
}
