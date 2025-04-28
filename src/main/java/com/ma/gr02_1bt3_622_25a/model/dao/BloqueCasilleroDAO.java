package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.model.entity.UsuarioRol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BloqueCasilleroDAO extends GenericDAO<BloqueCasillero> {
    public BloqueCasilleroDAO() {
        super(BloqueCasillero.class);
    }

    public BloqueCasillero obtenerUltimo() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<BloqueCasillero> query = em.createNamedQuery("BloqueCasillero.findUltima", BloqueCasillero.class);

            BloqueCasillero bloqueCasillero;
            try {
                bloqueCasillero = query.getSingleResult();
            } catch (NoResultException e) {
                return null; // No existe el usuario
            }

            return bloqueCasillero;
        }
    }
}
