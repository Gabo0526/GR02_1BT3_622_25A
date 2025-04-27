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
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Aplicación iniciada. EntityManagerFactory listo para usar.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
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
