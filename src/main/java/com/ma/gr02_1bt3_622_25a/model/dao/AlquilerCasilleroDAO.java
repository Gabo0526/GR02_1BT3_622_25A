package com.ma.gr02_1bt3_622_25a.model.dao;

import com.ma.gr02_1bt3_622_25a.model.entity.AlquilerCasillero;
import jakarta.persistence.EntityManager;

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
}
