package com.ma.gr02_1bt3_622_25a;

import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;

public class Test {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.autenticar("jairo.quispe@epn.edu.ec", "12345david", "Estudiante");

        System.out.println(usuario);
    }
}
