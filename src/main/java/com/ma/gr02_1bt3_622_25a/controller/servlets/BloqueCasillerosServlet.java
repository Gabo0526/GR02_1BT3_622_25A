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
        String nombreBloque = request.getParameter("nombreBloque");

        BloqueCasillero bloqueCasillero = new BloqueCasillero();
        bloqueCasillero.setNroFilas(Integer.parseInt(nroFilas));
        bloqueCasillero.setNroColumnas(Integer.parseInt(nroColumnas));
        bloqueCasillero.setNombreBloque(nombreBloque);

        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        bloqueCasilleroDAO.save(bloqueCasillero);

        bloqueCasillero = bloqueCasilleroDAO.obtenerUltimoBloqueAgregado();

        CasilleroDAO casilleroDAO = new CasilleroDAO();
        crearYGuardarCasilleros(bloqueCasillero, nroFilas, nroColumnas, ancho, profundidad, altura, casilleroDAO);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private void crearYGuardarCasilleros(BloqueCasillero bloqueCasillero, String nroFilas, String nroColumnas,
                                         String ancho, String profundidad, String altura, CasilleroDAO casilleroDAO) {

        int totalCasilleros = Integer.parseInt(nroFilas) * Integer.parseInt(nroColumnas);
        BigDecimal anchoVal = BigDecimal.valueOf(Integer.parseInt(ancho));
        BigDecimal profundidadVal = BigDecimal.valueOf(Integer.parseInt(profundidad));
        BigDecimal alturaVal = BigDecimal.valueOf(Integer.parseInt(altura));

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
