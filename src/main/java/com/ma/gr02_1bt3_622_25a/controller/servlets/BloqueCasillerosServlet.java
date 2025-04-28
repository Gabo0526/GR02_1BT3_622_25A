package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.BloqueCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "BloqueCasillerosServlet", urlPatterns = {"/BloqueCasillerosServlet"})
public class BloqueCasillerosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String nroFilas = request.getParameter("nroFilas");
        String nroColumnas = request.getParameter("nroColumnas");
        String ancho = request.getParameter("ancho");
        String profundidad = request.getParameter("profundidad");
        String altura = request.getParameter("altura");

        BloqueCasillero bloqueCasillero = new BloqueCasillero();
        bloqueCasillero.setNroFilas(Integer.parseInt(nroFilas));
        bloqueCasillero.setNroColumnas(Integer.parseInt(nroColumnas));

        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        bloqueCasilleroDAO.save(bloqueCasillero);

        bloqueCasillero = bloqueCasilleroDAO.obtenerUltimo();

        CasilleroDAO casilleroDAO = new CasilleroDAO();

        for (int i = 0; i < Integer.parseInt(nroFilas) * Integer.parseInt(nroColumnas); i++) {
            Casillero casillero = new Casillero();
            casillero.setIdBloque(bloqueCasillero);
            casillero.setNumero(i + 1);
            casillero.setAncho(BigDecimal.valueOf(Integer.parseInt(ancho)));
            casillero.setProfundidad(BigDecimal.valueOf(Integer.parseInt(profundidad)));
            casillero.setAltura(BigDecimal.valueOf(Integer.parseInt(altura)));
            casilleroDAO.save(casillero);
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
