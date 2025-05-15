package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.AlquilerCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.BloqueCasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.CasilleroDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
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
        String idBloque = request.getParameter("idBloque") == null ?  (String) request.getAttribute("idBloque"): request.getParameter("idBloque");

        if (idBloque == null) {
            idBloque = "1"; // Valor por defecto si no se proporciona idBloque
        }
        HttpSession session = request.getSession();
        BloqueCasilleroDAO bloqueCasilleroDAO = new BloqueCasilleroDAO();
        CasilleroDAO casilleroDAO = new CasilleroDAO();
        AlquilerCasilleroDAO alquilerCasilleroDAO = new AlquilerCasilleroDAO();

        BloqueCasillero bloqueCasillero = bloqueCasilleroDAO.find(Integer.parseInt(idBloque));
        List<Casillero> casilleros = casilleroDAO.obtenerCasillerosPorBloque(bloqueCasillero);
        int nroBloques = bloqueCasilleroDAO.contarBloques();

        boolean existeAlquiler = alquilerCasilleroDAO.detectarAlquileresActivosPorUsuario((Usuario) session.getAttribute("usuario"));

        // HttpSession session = request.getSession();
        request.setAttribute("casilleros", casilleros);
        // session.setAttribute("casilleros", casilleros);
        request.setAttribute("bloque", bloqueCasillero);
        // session.setAttribute("bloque", bloqueCasillero);
        request.setAttribute("nroBloques", nroBloques);
        // session.setAttribute("nroBloques", nroBloques);
        // request.setAttribute("usuario", session.getAttribute("usuario"));
        request.setAttribute("existeAlquiler", existeAlquiler);

        // Parametros de la peticion:
        // idBloque, casilleros, bloque, nroBloques
        request.getRequestDispatcher("viewLockers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idCasillero = request.getParameter("idCasillero");
        String estado = request.getParameter("disponibilidadSelect");

        CasilleroDAO casilleroDAO = new CasilleroDAO();

        if (casilleroDAO.actualizarEstadoCasillero(Integer.parseInt(idCasillero), estado)) {
            // Esto redirige a la misma URL pero con el método GET
            response.sendRedirect(request.getRequestURI() + "?idBloque=" + request.getParameter("idBloque"));
        } else {
            response.sendRedirect("home.jsp");
        }
    }
}
