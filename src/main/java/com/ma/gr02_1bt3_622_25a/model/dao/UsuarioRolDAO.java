package com.ma.gr02_1bt3_622_25a.model.dao;

import com.google.protobuf.Empty;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.model.entity.UsuarioRol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.mapping.Map;

import java.util.Collections;
import java.util.List;

public class UsuarioRolDAO extends GenericDAO<UsuarioRol> {
    public UsuarioRolDAO() {
        super(UsuarioRol.class);
    }

    public List<UsuarioRol> obtenerRoles(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<UsuarioRol> query = em.createNamedQuery("UsuarioRol.findByUser", UsuarioRol.class);
            query.setParameter(1, usuario);

            return query.getResultList().isEmpty() ? Collections.emptyList() : query.getResultList();

        } finally {
            em.close();
        }
    }
}
