package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.model.entity.UsuarioRol;
import jakarta.persistence.*;

import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario autenticarPorCredenciales(String correo, String clave) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findUserByCredentials", Usuario.class)
                    .setParameter(1, correo)
                    .setParameter(2, clave);

            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null; // No existe el usuario
            }
        }
    }
}
