package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class BloqueCasilleroDAO extends GenericDAO<BloqueCasillero> {
    public BloqueCasilleroDAO() {
        super(BloqueCasillero.class);
    }

    public BloqueCasillero obtenerUltimoBloqueAgregado() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<BloqueCasillero> query = em.createNamedQuery("BloqueCasillero.findLast", BloqueCasillero.class);

            BloqueCasillero bloqueCasillero;
            try {
                bloqueCasillero = query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }

            return bloqueCasillero;
        }
    }

    public int contarBloques() {
        try (EntityManager em = emf.createEntityManager()) {
            try {
                TypedQuery<Long> query = em.createNamedQuery("BloqueCasillero.countAll", Long.class);
                Long resultado = query.getSingleResult();
                return resultado.intValue();
            } catch (NoResultException e) {
                return 0; // Si no hay bloques, el conteo es 0
            }
        }
    }
}
