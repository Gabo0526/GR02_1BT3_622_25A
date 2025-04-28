package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.BloqueCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

public class CasilleroDAO extends GenericDAO<Casillero> {
    public CasilleroDAO() {
        super(Casillero.class);
    }

    @Override
    public List<Casillero> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT c FROM Casillero c JOIN fetch c.idBloque";
            return em.createQuery(jpql, entityClass).getResultList();
        }
    }

    public List<Casillero> obtenerPorBloque(BloqueCasillero bloque) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Casillero> query = em.createNamedQuery("Casillero.findByBloque", Casillero.class)
                    .setParameter(1, bloque);

            return query.getResultList().isEmpty() ? Collections.emptyList() : query.getResultList();
        }
    }

    public boolean actualizarEstadoCasillero(Integer idCasillero, String estado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createNamedQuery("Casillero.updateState")
                    .setParameter(1, estado)
                    .setParameter(2, idCasillero);

            int filasAfectadas = query.executeUpdate();

            tx.commit();

            return filasAfectadas > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

}
