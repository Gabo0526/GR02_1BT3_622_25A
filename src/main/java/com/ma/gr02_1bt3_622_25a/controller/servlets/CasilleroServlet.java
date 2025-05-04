package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.BloqueCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.math.BigDecimal;
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

        int nroBloques = bloqueCasilleroDAO.contarBloques();

        for (Casillero casillero : casilleros) {
            System.out.println(casillero);
        }
        System.out.println(casilleros.size());

        HttpSession session = request.getSession();
        request.setAttribute("casilleros", casilleros);
        session.setAttribute("casilleros", casilleros);
        request.setAttribute("bloque", bloqueCasillero);
        session.setAttribute("bloque", bloqueCasillero);
        request.setAttribute("nroBloques", nroBloques);
        session.setAttribute("nroBloques", nroBloques);
        request.setAttribute("usuario", session.getAttribute("usuario"));

        request.getRequestDispatcher("viewLockers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String idCasillero = request.getParameter("idCasillero");
        String estado = request.getParameter("disponibilidadSelect");

        System.out.println(idCasillero);

        CasilleroDAO casilleroDAO = new CasilleroDAO();

        if (casilleroDAO.actualizarEstadoCasillero(Integer.parseInt(idCasillero), estado)) {
            request.setAttribute("idBloque", "1");
            // Esto redirige a la misma URL pero con el método GET
            response.sendRedirect(request.getRequestURI() + "?idBloque=" + request.getAttribute("idBloque"));
        } else {
            response.sendRedirect("home.jsp");
        }
    }
}
