package com.ma.gr02_1bt3_622_25a.controller.servlets;

import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.service.ServicioUsuario;
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

        ServicioUsuario servicioUsuario = new ServicioUsuario();
        // Autenticación del usuario con rol
        Usuario usuario = servicioUsuario.autenticarConRol(correo, clave, rol);

        if (usuario != null) {
            // Guardar el usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("nombreUsuario", usuario.getNombre() + " " + usuario.getApellido());
            session.setAttribute("rolUsuario", rol);

            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String codigoUnico = request.getParameter("codigoUnico");
        String correo = request.getParameter("email");
        String clave = request.getParameter("password");
        String facultad = request.getParameter("facultad");

        Usuario usuario = new Usuario();

        usuario.setCedula(cedula);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCodigoUnico(codigoUnico);
        usuario.setCorreo(correo);
        usuario.setClave(clave);
        usuario.setFacultad(facultad);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.save(usuario);

        response.sendRedirect("index.jsp");
    }
}
