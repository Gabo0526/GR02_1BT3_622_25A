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

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "BloqueCasillerosServlet", urlPatterns = {"/BloqueCasillerosServlet"})
public class BloqueCasillerosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nroFilas = request.getParameter("nroFilas");
        String nroColumnas = request.getParameter("nroColumnas");
        String ancho = request.getParameter("ancho");
        String profundidad = request.getParameter("profundidad");
        String altura = request.getParameter("altura");
        String nombreBloque = request.getParameter("nombreBloque");

        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        BloqueCasillero bloqueCasillero = new BloqueCasillero();
        bloqueCasillero.setNroFilas(Integer.parseInt(nroFilas));
        bloqueCasillero.setNroColumnas(Integer.parseInt(nroColumnas));
        bloqueCasillero.setNombreBloque(nombreBloque);
        bloqueCasilleroDAO.save(bloqueCasillero);

        bloqueCasillero = bloqueCasilleroDAO.obtenerUltimoBloqueAgregado();

        crearYGuardarCasilleros(bloqueCasillero, Integer.parseInt(nroFilas) * Integer.parseInt(nroColumnas), Double.parseDouble(ancho), Double.parseDouble(profundidad), Double.parseDouble(altura));

        response.sendRedirect("home.jsp");
    }

    private void crearYGuardarCasilleros(BloqueCasillero bloqueCasillero, int totalCasilleros, Double ancho, Double profundidad, Double altura) {

        CasilleroDAO casilleroDAO = new CasilleroDAO();
        BigDecimal anchoVal = BigDecimal.valueOf(ancho);
        BigDecimal profundidadVal = BigDecimal.valueOf(profundidad);
        BigDecimal alturaVal = BigDecimal.valueOf(altura);

        for (int i = 0; i < totalCasilleros; i++) {
            Casillero casillero = new Casillero();
            casillero.setIdBloque(bloqueCasillero);
            casillero.setNumero(i + 1);
            casillero.setAncho(anchoVal);
            casillero.setProfundidad(profundidadVal);
            casillero.setAltura(alturaVal);
            casilleroDAO.save(casillero);
        }
    }
}
