# Imagen base con Tomcat
FROM tomcat:11.0.7-jdk17

# Elimina la aplicación por defecto (opcional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu archivo WAR al directorio webapps de Tomcat
COPY target/GR02_1BT3_622_25A-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto 8080
EXPOSE 8080

# Comando por defecto para iniciar Tomcat
CMD ["catalina.sh", "run"]
