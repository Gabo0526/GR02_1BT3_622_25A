package com.ma.gr02_1bt3_622_25a.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory cerrado correctamente.");
        }
    }
}
