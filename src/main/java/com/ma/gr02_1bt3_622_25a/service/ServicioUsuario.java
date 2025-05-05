package com.ma.gr02_1bt3_622_25a.service;

import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioDAO;
import com.ma.gr02_1bt3_622_25a.model.dao.UsuarioRolDAO;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.model.entity.UsuarioRol;

import java.util.List;

public class ServicioUsuario {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();

    public Usuario autenticarConRol(String correo, String clave, String rol) {
        Usuario usuario = usuarioDAO.autenticarPorCredenciales(correo, clave);
        if (usuario == null) {
            return null;
        }

        List<UsuarioRol> rolesUsuario = usuarioRolDAO.obtenerRolesPorUsuario(usuario);
        boolean tieneRol = rolesUsuario.stream()
                .anyMatch(usuarioRol -> rol.equals(usuarioRol.getIdRol().getNombreRol()));

        return tieneRol ? usuario : null;
    }
}
