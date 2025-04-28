package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.model.entity.UsuarioRol;
import jakarta.persistence.*;

import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario autenticar(String correo, String clave, String rol) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findUser", Usuario.class)
                    .setParameter(1, correo)
                    .setParameter(2, clave);

            Usuario usuario;
            try {
                usuario = query.getSingleResult();
            } catch (NoResultException e) {
                return null; // No existe el usuario
            }

            UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
            List<UsuarioRol> rolesUsuario = usuarioRolDAO.obtenerRoles(usuario);

            boolean tieneRol = rolesUsuario.stream()
                    .anyMatch(usuarioRol -> rol.equals(usuarioRol.getIdRol().getNombreRol()));

            return tieneRol ? usuario : null;
        }
    }
}
