package com.ma.gr02_1bt3_622_25a.utils;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener("Application Lifecycle Listener")
public class AppContextListener implements ServletContextListener {
    // private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Aplicación iniciada. Iniciando EntityManagerFactory y realizando consulta de prueba...");

        try (var em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("SELECT 1").getSingleResult(); // Consulta de calentamiento
            em.getTransaction().commit();
            System.out.println("Conexión inicial establecida correctamente.");
        } catch (Exception e) {
            System.err.println("Error durante el calentamiento de JPA/Hibernate: " + e.getMessage());
        }

        /*// Mantener la conexión viva
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try (var em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
                em.getTransaction().begin();
                em.createNativeQuery("SELECT 1").getSingleResult();
                em.getTransaction().commit();
                System.out.println("Heartbeat enviado.");
            } catch (Exception e) {
                System.err.println("Error en heartbeat: " + e.getMessage());
            }
        }, 5, 10, TimeUnit.MINUTES); // Espera 5 minutos, luego cada 10 minutos*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            System.out.println("Heartbeat detenido.");
        }*/

        // Cerrar recursos JPA
        JpaUtil.cerrar();

        // Apagar el hilo de limpieza de conexiones abandonadas de MySQL
        AbandonedConnectionCleanupThread.checkedShutdown();
        System.out.println("Hilo de limpieza de conexiones abandonadas detenido.");

        // Anular el registro de todos los drivers JDBC para evitar memory leaks
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Driver JDBC desregistrado: " + driver);
            } catch (SQLException e) {
                System.err.println("Error al desregistrar driver: " + driver + " - " + e.getMessage());
            }
        }
    }
}
