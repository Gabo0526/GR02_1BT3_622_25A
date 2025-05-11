package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Casillero;
import com.ma.gr02_1bt3_622_25a.model.entity.Usuario;
import com.ma.gr02_1bt3_622_25a.service.ServicioAlquilerCasillero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.Collections;
import java.util.List;

public class AlquilerCasilleroDAO extends GenericDAO<AlquilerCasillero> {
    public AlquilerCasilleroDAO() {
        super(AlquilerCasillero.class);
    }

    @Override
    public List<AlquilerCasillero> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT a FROM AlquilerCasillero a join fetch a.idUsuario join fetch a.idCasillero join fetch a.idCasillero.idBloque";
            return em.createQuery(jpql, entityClass).getResultList();
        }
    }

    public boolean actualizarAlquilerCasillero(int idAlquiler, String estado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createNamedQuery("AlquilerCasillero.updateState")
                    .setParameter(1, estado)
                    .setParameter(2, idAlquiler);

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

    public List<AlquilerCasillero> obtenerAlquileresPorUsuario(Usuario usuario) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<AlquilerCasillero> query = em.createNamedQuery("AlquilerCasillero.findActiveByUser", AlquilerCasillero.class)
                    .setParameter(1, usuario);

            return new ServicioAlquilerCasillero().procesarResultadosConsulta(query.getResultList());
        }
    }

    public boolean intercambiarCasillero(Casillero nuevoCasillero, int idAlquiler) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createNamedQuery("AlquilerCasillero.exchangeLocker")
                    .setParameter(1, nuevoCasillero)
                    .setParameter(2, idAlquiler);

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
